package com.store.favquotes.feature.quotes.ui.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
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
                        Timber.d("")
                    },
                    goToSearchQuotes = {
                        Timber.d("")
                    },
                    goToLogin = {
                        Timber.d("")
                    },
                )
            }
        }

    }

    sealed class Screens(override val route: String) : NavigationRoute() {
        object MainScreen : Screens("quotes/main")

    }
}
