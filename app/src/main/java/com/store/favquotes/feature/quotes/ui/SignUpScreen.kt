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
import com.store.favquotes.feature.quotes.ui.actions.SignUpStateEffect.Action.*
import com.store.favquotes.feature.quotes.ui.actions.SignUpStateEffect.Event
import com.store.favquotes.feature.quotes.ui.viewmodel.SignUpViewModel
import com.store.favquotes.ui.theme.spacing
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    goBack: () -> Unit,
) {
    val state = viewModel.state.collectAsStateLifecycleAware().value

    SignUpScreen(
        isLoading = state.isLoading,
        hasError = state.hasError,
        errorMessage = state.errorMessage,
        isSignUpEnabled = state.isSignUpEnabled(),
        username = state.userName,
        email = state.email,
        password = state.password,
        usernameChanged = { viewModel.onAction(action = OnUsernameValueChanged(it)) },
        emailChanged = { viewModel.onAction(action = OnEmailValueChanged(it)) },
        passwordChanged = { viewModel.onAction(action = OnPasswordValueChanged(it)) },
        signUpAction = { viewModel.onAction(action = OnSignUp) },
        backAction = { viewModel.onAction(action = OnBack) },
    )

    HandleEvents(
        event = viewModel.event,
        goBack = goBack,
    )
}

@Composable
private fun SignUpScreen(
    isLoading: Boolean,
    hasError: Boolean,
    errorMessage: String?,
    isSignUpEnabled: Boolean,
    username: String,
    email: String,
    password: String,
    usernameChanged: (String) -> Unit,
    emailChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
    signUpAction: () -> Unit,
    backAction: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(stringResource(id = R.string.sign_up))
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
                value = username,
                onValueChange = { value ->
                    usernameChanged(value)
                },
                label = {
                    Text(text = stringResource(id = R.string.username))
                }
            )
            OutlinedTextField(
                modifier = Modifier.padding(top = MaterialTheme.spacing.medium),
                value = email,
                onValueChange = { value ->
                    emailChanged(value)
                },
                label = {
                    Text(text = stringResource(id = R.string.email))
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
                    enabled = isSignUpEnabled,
                    shape = CircleShape,
                    contentPadding = PaddingValues(all = MaterialTheme.spacing.medium),
                    onClick = signUpAction,
                ) {
                    Text(text = stringResource(id = R.string.sign_up))
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
fun SignUpScreenPreview() {
    SignUpScreen(
        isLoading = false,
        hasError = false,
        errorMessage = null,
        isSignUpEnabled = false,
        username = "",
        email = "",
        password = "",
        usernameChanged = {},
        emailChanged = {},
        passwordChanged = {},
        signUpAction = {},
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
