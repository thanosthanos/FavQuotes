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
import com.store.favquotes.feature.quotes.ui.actions.ListOfQuotesStateEffect
import com.store.favquotes.feature.quotes.ui.actions.ListOfQuotesStateEffect.Action.OnBack
import com.store.favquotes.feature.quotes.ui.viewmodel.ListOfQuotesViewModel
import com.store.favquotes.ui.theme.spacing
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun PublicQuotesScreen(
    viewModel: ListOfQuotesViewModel = hiltViewModel(),
    goBack: () -> Unit,
) {
    val state = viewModel.state.collectAsStateLifecycleAware().value

    PublicQuotesScreen(
        isLoading = state.isLoading,
        hasError = state.hasError,
        quotes = state.quotes,
        backAction = { viewModel.onAction(action = OnBack) },
    )

    HandleEvents(
        event = viewModel.event,
        goBack = goBack,
    )
}

@Composable
private fun PublicQuotesScreen(
    isLoading: Boolean,
    hasError: Boolean,
    quotes: List<Quote>,
    backAction: () -> Unit,
) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(stringResource(id = R.string.list_of_public_quotes))
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = backAction) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            })
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
            else -> QuotesList(quotes = quotes)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListOfQuotesPreview() {
    PublicQuotesScreen(
        isLoading = false,
        hasError = false,
        quotes = listOf(),
        backAction = {}
    )
}

@Composable
private fun HandleEvents(
    event: Channel<ListOfQuotesStateEffect.Event>,
    goBack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        event.receiveAsFlow().collectLatest { event ->
            when (event) {
                ListOfQuotesStateEffect.Event.Navigate.Back -> goBack()
            }
        }
    }
}
