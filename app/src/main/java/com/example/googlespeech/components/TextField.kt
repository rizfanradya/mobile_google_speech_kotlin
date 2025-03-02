package com.example.googlespeech.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingText: String? = null,
    textFieldState: TextFieldState,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
    onTextChanged: (String) -> Unit,
) {
    if (isPassword) {
        PasswordTextField(
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            trailingText = trailingText,
            textFieldState = textFieldState,
            hint = hint,
            onLeadingClick = onLeadingClick,
            onTrailingClick = onTrailingClick,
            onTextChanged = onTextChanged
        )
    } else {
        TextTextField(
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            trailingText = trailingText,
            textFieldState = textFieldState,
            hint = hint,
            keyboardType = keyboardType,
            onLeadingClick = onLeadingClick,
            onTrailingClick = onTrailingClick,
            onTextChanged = onTextChanged
        )
    }
}

@Composable
fun TextTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingText: String? = null,
    textFieldState: TextFieldState,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
    onTextChanged: (String) -> Unit,
) {
    val previousText = remember { mutableStateOf(textFieldState.text) }

    BasicTextField(state = textFieldState,
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        lineLimits = TextFieldLineLimits.SingleLine,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        modifier = modifier,
        decorator = { innerTextField ->
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) {
                        Icon(imageVector = leadingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                            modifier = Modifier.clickable { onLeadingClick() })
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (textFieldState.text.isEmpty()) {
                            Text(
                                text = hint,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.4f),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        innerTextField()
                        if (previousText.value != textFieldState.text) {
                            previousText.value = textFieldState.text
                            onTextChanged(textFieldState.text.toString())
                        }
                    }

                    if (trailingIcon != null) {
                        Icon(imageVector = trailingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable { onTrailingClick() })
                    } else if (trailingText != null) {
                        Text(text = trailingText,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable { onTrailingClick() })
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider(modifier = Modifier.alpha(0.7f))
            }
        })
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingText: String? = null,
    textFieldState: TextFieldState,
    hint: String,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
    onTextChanged: (String) -> Unit,
) {
    val previousText = remember { mutableStateOf(textFieldState.text) }

    BasicSecureTextField(state = textFieldState,
        textObfuscationMode = TextObfuscationMode.Hidden,
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        modifier = modifier,
        decorator = { innerTextField ->
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) {
                        Icon(imageVector = leadingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                            modifier = Modifier.clickable { onLeadingClick() })
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (textFieldState.text.isEmpty()) {
                            Text(
                                text = hint,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.4f),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        innerTextField()
                        if (previousText.value != textFieldState.text) {
                            previousText.value = textFieldState.text
                            onTextChanged(textFieldState.text.toString())
                        }
                    }

                    if (trailingIcon != null) {
                        Icon(imageVector = trailingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable { onTrailingClick() })
                    } else if (trailingText != null) {
                        Text(text = trailingText,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable { onTrailingClick() })
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider(modifier = Modifier.alpha(0.7f))
            }
        })
}
