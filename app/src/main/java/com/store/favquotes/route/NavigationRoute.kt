package com.store.favquotes.route

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

abstract class NavigationRoute {
    abstract val route: String
    open val args: List<NamedNavArgument> = emptyList()
}

fun NavGraphBuilder.routeComposable(
    route: NavigationRoute,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(route = route.route, arguments = route.args, content = content)
}
