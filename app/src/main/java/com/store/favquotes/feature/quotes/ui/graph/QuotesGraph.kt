package com.store.favquotes.feature.quotes.ui.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.navigation
import com.store.favquotes.feature.quotes.ui.*
import com.store.favquotes.route.NavigationRoute
import com.store.favquotes.route.routeComposable

object QuotesGraph : NavigationRoute() {
    override val route: String = "quotes"

    @OptIn(ExperimentalAnimationApi::class)
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
                    goToSignUp = {
                        navController.navigate(Screens.SignUp.route)
                    },
                )
            }
            routeComposable(Screens.ListOfQuotes) {
                PublicQuotesScreen(
                    goBack = { navController.popBackStack() },
                )
            }

            routeComposable(Screens.SearchQuotes) {
                SearchQuotesScreen(
                    goBack = { navController.popBackStack() },
                )
            }

            routeComposable(Screens.Login) {
                LoginScreen(
                    goBack = { navController.popBackStack() },
                )
            }

            routeComposable(Screens.SignUp) {
                SignUpScreen(
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
        object SignUp : Screens("quotes/sign_up")
    }
}
