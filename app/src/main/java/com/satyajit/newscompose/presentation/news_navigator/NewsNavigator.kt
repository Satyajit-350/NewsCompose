package com.satyajit.newscompose.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.satyajit.newscompose.R
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.presentation.bookmark.BookmarkScreen
import com.satyajit.newscompose.presentation.bookmark.BookmarkViewModel
import com.satyajit.newscompose.presentation.details.DetailsEvent
import com.satyajit.newscompose.presentation.details.DetailsScreen
import com.satyajit.newscompose.presentation.details.DetailsViewModel
import com.satyajit.newscompose.presentation.home.HomeScreen
import com.satyajit.newscompose.presentation.home.HomeViewModel
import com.satyajit.newscompose.presentation.navgraph.Routes
import com.satyajit.newscompose.presentation.news_navigator.components.BottomNavigationItem
import com.satyajit.newscompose.presentation.news_navigator.components.NewsBottomNavigation
import com.satyajit.newscompose.presentation.search.SearchScreen
import com.satyajit.newscompose.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(R.drawable.ic_home, "Home"),
            BottomNavigationItem(R.drawable.ic_search, "Search"),
            BottomNavigationItem(R.drawable.ic_bookmark, "Bookmark"),
        )
    }

    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember (key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Routes.HomeScreen.route -> 0
            Routes.SearchScreen.route -> 1
            Routes.BookMarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible  = remember(key1 = backStackState) {
        backStackState?.destination?.route == Routes.HomeScreen.route ||
                backStackState?.destination?.route == Routes.SearchScreen.route ||
                backStackState?.destination?.route == Routes.BookMarkScreen.route
    }

    //Scaffold
    //basically used for dividing the screen into parts like top app bar, bottom navigation bar

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(isBottomBarVisible){
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTap(
                                navController,
                                Routes.HomeScreen.route
                            )

                            1 -> navigateToTap(
                                navController,
                                Routes.SearchScreen.route
                            )

                            2 -> navigateToTap(
                                navController,
                                Routes.BookMarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Routes.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(
                route = Routes.HomeScreen.route
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTap(navController, Routes.SearchScreen.route)
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(navController, article)
                    }
                )
            }

            composable(route = Routes.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(navController, article)
                    }
                )
            }

            composable(route = Routes.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()

                //TODO side effect handling
                if(viewModel.sideEffect!=null){
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
            }

            composable(route = Routes.BookMarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(
                    state = viewModel.state.value,
                    navigateToDetails = { article ->
                       navigateToDetails(navController,article)
                    }
                )
            }
        }
    }

}

private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop =
                true //to not create instance of screens when we click on screen multiple times
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        Routes.DetailsScreen.route
    )
}