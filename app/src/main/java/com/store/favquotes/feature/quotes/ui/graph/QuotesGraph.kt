package com.store.favquotes.feature.quotes.ui.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.store.favquotes.feature.quotes.ui.ListOfQuotes
import com.store.favquotes.feature.quotes.ui.MainScreen
import com.store.favquotes.route.NavigationRoute
import com.store.favquotes.route.routeComposable
import timber.log.Timber

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
                        Timber.d("")
                    },
                    goToLogin = {
                        Timber.d("")
                    },
                )
            }
            routeComposable(Screens.ListOfQuotes) {
                ListOfQuotes(
                    goBack = { navController.popBackStack() },
                )
            }
        }

    }

    sealed class Screens(override val route: String) : NavigationRoute() {
        object MainScreen : Screens("quotes/main")
        object ListOfQuotes : Screens("quotes/list_of_quotes")

    }
}
