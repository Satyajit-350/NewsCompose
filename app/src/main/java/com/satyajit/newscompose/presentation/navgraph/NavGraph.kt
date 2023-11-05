package com.satyajit.newscompose.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.satyajit.newscompose.presentation.bookmark.BookmarkScreen
import com.satyajit.newscompose.presentation.bookmark.BookmarkViewModel
import com.satyajit.newscompose.presentation.news_navigator.NewsNavigator
import com.satyajit.newscompose.presentation.onboarding.OnBoardingScreen
import com.satyajit.newscompose.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){
        navigation(
            route = Routes.AppStartNavigation.route,
            startDestination = Routes.OnBoardingScreen.route
        ){
            composable(
                route = Routes.OnBoardingScreen.route
            ){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
//                        onEvent = {
//                            viewModel.onEvent(it)
//                        }
                    //alternate way
                    onEvent = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Routes.NewsNavigation.route,
            startDestination = Routes.NewsNavigatorScreen.route
        ){
            composable(
                route = Routes.NewsNavigatorScreen.route
            ){
                NewsNavigator()
            }
        }
    }
}