package com.store.favquotes.feature.quotes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.store.favquotes.R
import com.store.favquotes.extension.collectAsStateLifecycleAware
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.Action.*
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.Event.Navigate.*
import com.store.favquotes.feature.quotes.ui.viewmodel.MainViewModel
import com.store.favquotes.ui.theme.spacing
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    goToPublicQuotes: () -> Unit,
    goToSearchQuotes: () -> Unit,
    goToLogin: () -> Unit,
) {
    val state = viewModel.state.collectAsStateLifecycleAware().value
    MainScreen(
        quote = state.quote,
        browseQuotesAction = { viewModel.onAction(action = OnPublicQuotes) },
        searchQuotesAction = { viewModel.onAction(action = OnSearchQuotes) },
        loginAction = { viewModel.onAction(action = OnLogin) },
    )

    HandleEvents(
        event = viewModel.event,
        goToPublicQuotes = goToPublicQuotes,
        goToSearchQuotes = goToSearchQuotes,
        goToLogin = goToLogin,
    )
}

@Composable
private fun HandleEvents(
    event: Channel<MainScreenStateEffect.Event>,
    goToPublicQuotes: () -> Unit,
    goToSearchQuotes: () -> Unit,
    goToLogin: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        event.receiveAsFlow().collectLatest { event ->
            when (event) {
                is ToLogin -> goToLogin()
                is ToPublicQuotes -> goToPublicQuotes()
                is ToSearchQuotes -> goToSearchQuotes()
            }
        }
    }
}

@Composable
private fun MainScreen(
    quote: String?,
    browseQuotesAction: () -> Unit,
    searchQuotesAction: () -> Unit,
    loginAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = R.string.app_title),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.h4,
        )
        Text(
            modifier = Modifier.padding(top = MaterialTheme.spacing.medium),
            text = stringResource(id = R.string.fav_quotes_tile),
            style = MaterialTheme.typography.h4,
        )
        quote?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.subtitle1,
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.large),
            shape = CircleShape,
            onClick = browseQuotesAction,
            contentPadding = PaddingValues(all = MaterialTheme.spacing.medium),
        ) {
            Text(text = stringResource(id = R.string.public_quotes))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.medium),
            shape = CircleShape,
            onClick = searchQuotesAction,
            contentPadding = PaddingValues(all = MaterialTheme.spacing.medium),
        ) {
            Text(text = stringResource(id = R.string.search_quotes))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.medium),
            shape = CircleShape,
            onClick = loginAction,
            contentPadding = PaddingValues(all = MaterialTheme.spacing.medium),
        ) {
            Text(text = stringResource(id = R.string.login))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen(
        quote = "This is a quote",
        browseQuotesAction = {},
        searchQuotesAction = {},
        loginAction = {},
    )
}
