package com.store.favquotes.feature.quotes.ui.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.store.favquotes.feature.quotes.ui.ListOfQuotes
import com.store.favquotes.feature.quotes.ui.LoginView
import com.store.favquotes.feature.quotes.ui.MainScreen
import com.store.favquotes.feature.quotes.ui.SearchQuotes
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
                MainScreen(
                    goToPublicQuotes = {
                        navController.navigate(Screens.ListOfQuotes.route)
                    },
                    goToSearchQuotes = {
                        navController.navigate(Screens.SearchQuotes.route)
                    },
                    goToLogin = {
                        navController.navigate(Screens.Login.route)
                    },
                )
            }
            routeComposable(Screens.ListOfQuotes) {
                ListOfQuotes(
                    goBack = { navController.popBackStack() },
                )
            }

            routeComposable(Screens.SearchQuotes) {
                SearchQuotes(
                    goBack = { navController.popBackStack() },
                )
            }

            routeComposable(Screens.Login) {
                LoginView(
                    goBack = { navController.popBackStack() },
                )
            }
        }

    }

    sealed class Screens(override val route: String) : NavigationRoute() {
        object MainScreen : Screens("quotes/main")
        object ListOfQuotes : Screens("quotes/list_of_quotes")
        object SearchQuotes : Screens("quotes/search_quotes")
        object Login : Screens("quotes/login")
    }
}
