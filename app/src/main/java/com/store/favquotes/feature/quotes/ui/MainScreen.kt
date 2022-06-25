package com.store.favquotes.feature.quotes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.store.favquotes.R
import com.store.favquotes.extension.collectAsStateLifecycleAware
import com.store.favquotes.feature.quotes.domain.model.Quote
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
    goToSignUp: () -> Unit,
) {
    val state = viewModel.state.collectAsStateLifecycleAware().value
    MainScreen(
        isLoading = state.isLoading,
        hasError = state.hasError,
        quote = state.quote,
        browseQuotesAction = { viewModel.onAction(action = OnPublicQuotes) },
        searchQuotesAction = { viewModel.onAction(action = OnSearchQuotes) },
        loginAction = { viewModel.onAction(action = OnLogin) },
        signUpAction = { viewModel.onAction(action = OnSignUp) },
    )

    HandleEvents(
        event = viewModel.event,
        goToPublicQuotes = goToPublicQuotes,
        goToSearchQuotes = goToSearchQuotes,
        goToLogin = goToLogin,
        goToSignUp = goToSignUp,
    )
}

@Composable
private fun HandleEvents(
    event: Channel<MainScreenStateEffect.Event>,
    goToPublicQuotes: () -> Unit,
    goToSearchQuotes: () -> Unit,
    goToLogin: () -> Unit,
    goToSignUp: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        event.receiveAsFlow().collectLatest { event ->
            when (event) {
                is ToLogin -> goToLogin()
                is ToPublicQuotes -> goToPublicQuotes()
                is ToSearchQuotes -> goToSearchQuotes()
                ToSignUp -> goToSignUp()
            }
        }
    }
}

@Composable
private fun MainScreen(
    isLoading: Boolean,
    hasError: Boolean,
    quote: Quote?,
    browseQuotesAction: () -> Unit,
    searchQuotesAction: () -> Unit,
    loginAction: () -> Unit,
    signUpAction: () -> Unit,
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
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.padding(top = MaterialTheme.spacing.medium),
            text = stringResource(id = R.string.fav_quotes_tile),
            style = MaterialTheme.typography.h6,
        )

        when {
            isLoading -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.spacing.small),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            hasError -> {
                Text(
                    text = stringResource(id = R.string.error_could_not_load_quote),
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            quote != null -> {
                Text(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small),
                    text = quote.body,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
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
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.medium),
            shape = CircleShape,
            onClick = signUpAction,
            contentPadding = PaddingValues(all = MaterialTheme.spacing.medium),
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen(
        isLoading = false,
        hasError = false,
        quote = Quote(
            tags = listOf(),
            isFavorite = false,
            authorPermalink = "",
            body = "This is a quote",
            id = 12,
            favoritesCount = 1,
            upvotesCount = 1,
            downvotesCount = 1,
            dialogue = false,
            author = "",
            url = "",
        ),
        browseQuotesAction = {},
        searchQuotesAction = {},
        loginAction = {},
        signUpAction = {},
    )
}
