package com.satyajit.newscompose.presentation.navgraph

import okhttp3.Route

sealed class Routes(
    val route: String
){
    object OnBoardingScreen: Routes(route = "onBoardingScreen")
    object HomeScreen: Routes(route = "homeScreen")
    object SearchScreen: Routes(route = "searchScreen")
    object BookMarkScreen: Routes(route = "bookmarkScreen")
    object DetailsScreen: Routes(route = "detailsScreen")

    //sub navigation
    object AppStartNavigation: Routes(route = "appStartNavigation")//route name
    object NewsNavigation: Routes(route = "newsNavigation")//route name
    object NewsNavigatorScreen: Routes(route = "newsNavigatorScreen")

}
