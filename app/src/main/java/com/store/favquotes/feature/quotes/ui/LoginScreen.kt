package com.store.favquotes.feature.quotes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.store.favquotes.R
import com.store.favquotes.extension.collectAsStateLifecycleAware
import com.store.favquotes.feature.quotes.ui.actions.LoginStateEffect.Action.*
import com.store.favquotes.feature.quotes.ui.actions.LoginStateEffect.Event
import com.store.favquotes.feature.quotes.ui.viewmodel.LoginViewModel
import com.store.favquotes.ui.theme.spacing
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    goBack: () -> Unit,
) {
    val state = viewModel.state.collectAsStateLifecycleAware().value

    LoginScreen(
        isLoading = state.isLoading,
        hasError = state.hasError,
        errorMessage = state.errorMessage,
        isLoginEnabled = state.isLoginEnabled(),
        usernameOrEmail = state.userNameOrEmail,
        password = state.password,
        usernameOrEmailChanged = { viewModel.onAction(action = OnUsernameValueChanged(it)) },
        passwordChanged = { viewModel.onAction(action = OnPasswordValueChanged(it)) },
        login = { viewModel.onAction(action = OnLogin) },
        backAction = { viewModel.onAction(action = OnBack) },
    )

    if (state.shouldShowSignedInDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.onAction(action = DismissSignInDialog) },
            title = {
                Text(text = stringResource(id = R.string.sign_in_success_title))
            },
            text = {
                Text(text = stringResource(id = R.string.sign_in_success_message))
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = MaterialTheme.spacing.medium),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        modifier = Modifier.wrapContentWidth(),
                        onClick = { viewModel.onAction(action = DismissSignInDialog) }
                    ) {
                        Text(stringResource(id = R.string.ok_title))
                    }
                }
            }
        )
    }

    HandleEvents(
        event = viewModel.event,
        goBack = goBack,
    )
}

@Composable
private fun LoginScreen(
    isLoading: Boolean,
    hasError: Boolean,
    errorMessage: String?,
    isLoginEnabled: Boolean,
    usernameOrEmail: String,
    password: String,
    usernameOrEmailChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
    login: () -> Unit,
    backAction: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(stringResource(id = R.string.login))
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = backAction) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.medium)
        ) {
            OutlinedTextField(
                modifier = Modifier.padding(top = MaterialTheme.spacing.medium),
                value = usernameOrEmail,
                onValueChange = { value ->
                    usernameOrEmailChanged(value)
                },
                label = {
                    Text(text = stringResource(id = R.string.username_or_email))
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small),
                value = password,
                onValueChange = { value ->
                    passwordChanged(value)
                },
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                visualTransformation = PasswordVisualTransformation(),
            )
            if (hasError) {
                val error = errorMessage ?: stringResource(id = R.string.error_general)
                Text(
                    text = error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.extraLarge),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = isLoginEnabled,
                    shape = CircleShape,
                    contentPadding = PaddingValues(all = MaterialTheme.spacing.medium),
                    onClick = login,
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
                if (isLoading) {
                    CircularProgressIndicator()
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    LoginScreen(
        isLoading = false,
        hasError = false,
        errorMessage = null,
        isLoginEnabled = false,
        usernameOrEmail = "",
        password = "",
        usernameOrEmailChanged = {},
        passwordChanged = {},
        login = {},
        backAction = {},
    )
}

@Composable
private fun HandleEvents(
    event: Channel<Event>,
    goBack: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        event.receiveAsFlow().collectLatest { event ->
            when (event) {
                Event.Navigate.Back -> goBack()
            }
        }
    }
}
