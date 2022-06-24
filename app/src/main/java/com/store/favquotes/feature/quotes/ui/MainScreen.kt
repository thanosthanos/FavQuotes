package com.store.favquotes.feature.quotes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.store.favquotes.R
import com.store.favquotes.ui.theme.spacing

@Composable
fun MainScreen() {
    Text(text = "Main screen")
}

@Composable
private fun MainScreen(
    quote: String,
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
        Text(
            text = quote,
            style = MaterialTheme.typography.subtitle1,
        )
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
