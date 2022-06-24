package com.store.favquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.store.favquotes.feature.quotes.ui.graph.QuotesGraph
import com.store.favquotes.feature.quotes.ui.graph.QuotesGraph.quotesGraph
import com.store.favquotes.ui.theme.FavQuotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavQuotesTheme {
                QuotesApp()
            }
        }
    }
}

@Composable
fun QuotesApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = QuotesGraph.route) {
        quotesGraph(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FavQuotesTheme {
        QuotesApp()
    }
}
