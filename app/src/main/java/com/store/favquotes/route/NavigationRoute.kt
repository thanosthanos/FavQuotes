package com.store.favquotes.route

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

val tweenSpec = tween<IntOffset>(durationMillis = 300, easing = FastOutSlowInEasing)

abstract class NavigationRoute {
    abstract val route: String
    open val args: List<NamedNavArgument> = emptyList()
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeComposable(
    route: NavigationRoute,
    enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideInHorizontally(initialOffsetX = { it/2 }, animationSpec = tweenSpec)
    },
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tweenSpec)
    },
    popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tweenSpec)
    },
    popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tweenSpec)
    },
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route.route,
        arguments = route.args,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content
    )
}
