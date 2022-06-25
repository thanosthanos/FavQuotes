package com.store.favquotes.feature.quotes.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.store.favquotes.ui.theme.spacing

const val DividerAlpha = 0.12f

@Composable
fun SearchView(
    state: String,
    hint: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(MaterialTheme.spacing.medium),
    onValueChange: (String) -> Unit
) {
    val iconColor = LocalContentColor.current.copy(LocalContentAlpha.current)
    val borderColor = MaterialTheme.colors.onSurface.copy(alpha = DividerAlpha)
    TextField(
        value = state,
        onValueChange = { value ->
            onValueChange(value)
        },
        placeholder = {
            Text(hint)
        },
        modifier = modifier.border(
            BorderStroke(
                1.dp,
                MaterialTheme.colors.onSurface.copy(alpha = DividerAlpha)
            )
        ),
        textStyle = MaterialTheme.typography.body1,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
            )
        },
        trailingIcon = {
            if (state.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onValueChange("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.medium)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = borderColor,
            unfocusedIndicatorColor = borderColor,
            cursorColor = iconColor,
            leadingIconColor = iconColor,
            trailingIconColor = iconColor,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    SearchView(
        state = "test",
        hint = "Searching...",
    ) {}
}
