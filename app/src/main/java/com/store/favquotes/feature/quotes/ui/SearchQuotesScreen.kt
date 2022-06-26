package com.store.favquotes.feature.quotes.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.store.favquotes.R
import com.store.favquotes.extension.collectAsStateLifecycleAware
import com.store.favquotes.feature.quotes.domain.model.Quote
import com.store.favquotes.feature.quotes.ui.actions.SearchQuotesStateEffect.Action.*
import com.store.favquotes.feature.quotes.ui.actions.SearchQuotesStateEffect.Event
import com.store.favquotes.feature.quotes.ui.actions.SearchQuotesStateEffect.Event.Navigate.Back
import com.store.favquotes.feature.quotes.ui.viewmodel.SearchQuotesViewModel
import com.store.favquotes.ui.theme.spacing
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun SearchQuotesScreen(
    viewModel: SearchQuotesViewModel = hiltViewModel(),
    goBack: () -> Unit,
) {
    val state = viewModel.state.collectAsStateLifecycleAware().value

    SearchQuotesScreen(
        isLoading = state.isLoading,
        hasError = state.hasError,
        quotes = state.quotes,
        hasQuotes = state.hasQuotes(),
        searchTerm = state.searchTerm,
        onValueChange = { viewModel.onAction(action = OnSearchTerm(it)) },
        backAction = { viewModel.onAction(action = OnBack) },
    )

    HandleEvents(
        event = viewModel.event,
        goBack = goBack,
    )
}

@Composable
private fun SearchQuotesScreen(
    isLoading: Boolean,
    hasError: Boolean,
    quotes: List<Quote>,
    hasQuotes: Boolean,
    searchTerm: String,
    onValueChange: (String) -> Unit,
    backAction: () -> Unit,
) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(stringResource(id = R.string.search_public_quotes))
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = backAction) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            })
        SearchView(
            state = searchTerm,
            hint = stringResource(id = R.string.search_hint),
            onValueChange = onValueChange,
        )
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.medium)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            hasError -> Text(
                modifier = Modifier.padding(all = MaterialTheme.spacing.medium),
                text = stringResource(id = R.string.error_general)
            )
            else -> {
                if(hasQuotes) {
                    QuotesList(quotes = quotes)
                } else {
                    Text(
                        modifier = Modifier.padding(all = MaterialTheme.spacing.medium),
                        text = stringResource(id = R.string.no_quotes_found)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchQuotesPreview() {
    SearchQuotesScreen(
        isLoading = false,
        hasError = false,
        quotes = listOf(),
        hasQuotes = true,
        searchTerm = "",
        onValueChange = {},
        backAction = {},
    )
}

@Composable
private fun HandleEvents(
    event: Channel<Event>,
    goBack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        event.receiveAsFlow().collectLatest { event ->
            when (event) {
                Back -> goBack()
            }
        }
    }
}
