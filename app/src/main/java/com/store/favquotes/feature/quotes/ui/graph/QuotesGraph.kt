package com.store.favquotes.feature.quotes.ui.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.store.favquotes.feature.quotes.ui.MainScreen
import com.store.favquotes.route.NavigationRoute
import com.store.favquotes.route.routeComposable

object QuotesGraph : NavigationRoute() {
    override val route: String = "quotes"

    fun NavGraphBuilder.quotesGraph(navController: NavController) {
        navigation(
            startDestination = Screens.MainScreen.route,
            route = QuotesGraph.route
        ) {
            routeComposable(Screens.MainScreen) {
                MainScreen()
            }
        }

    }

    sealed class Screens(override val route: String) : NavigationRoute() {
        object MainScreen : Screens("quotes/main")

    }
}
