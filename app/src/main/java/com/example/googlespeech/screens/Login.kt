package com.example.googlespeech.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.googlespeech.R
import com.example.googlespeech.api.config.ApiClient
import com.example.googlespeech.api.models.auth.LoginResponse
import com.example.googlespeech.components.AuthGuard
import com.example.googlespeech.components.AuthOption
import com.example.googlespeech.components.ErrorDialog
import com.example.googlespeech.components.TextField
import com.example.googlespeech.utils.Routes
import com.example.googlespeech.utils.isValidEmail
import com.example.googlespeech.utils.isValidPassword
import com.example.googlespeech.utils.saveToken
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {
    AuthGuard(navController) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val emailState = remember { TextFieldState() }
        val passwordState = remember { TextFieldState() }
        val emailError = remember { mutableStateOf("") }
        val passwordError = remember { mutableStateOf("") }
        val isLoading = remember { mutableStateOf(false) }
        val showDialog = remember { mutableStateOf(false) }
        val errorMessage = remember { mutableStateOf("") }

        val isFormValid =
            emailError.value.isEmpty() && passwordError.value.isEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty()

        if (showDialog.value) {
            ErrorDialog(showDialog.value, errorMessage.value) {
                showDialog.value = false
            }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                Image(
                    painter = painterResource(R.drawable.login),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth(0.25f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Login", fontWeight = FontWeight.SemiBold, fontSize = 30.sp
                )
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    textFieldState = emailState,
                    hint = "Email",
                    leadingIcon = Icons.Outlined.Email,
                    trailingIcon = Icons.Outlined.Check,
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.fillMaxWidth(),
                    onTextChanged = {
                        email.value = it
                        emailError.value = if (isValidEmail(it)) "" else "Invalid email"
                    },
                    isError = emailError.value.isNotEmpty()
                )
                if (emailError.value.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = emailError.value,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    textFieldState = passwordState,
                    hint = "Password",
                    leadingIcon = Icons.Outlined.Lock,
                    trailingText = "Forgot?",
                    isPassword = true,
                    modifier = Modifier.fillMaxWidth(),
                    onTextChanged = {
                        password.value = it
                        passwordError.value =
                            if (isValidPassword(it)) "" else "Password must be at least 8 characters, include uppercase, lowercase, a number, and a special character"
                    },
                    isError = passwordError.value.isNotEmpty()
                )
                if (passwordError.value.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = passwordError.value,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            ApiClient.loginService.androidLogin(email.value, password.value)
                                .enqueue(object : Callback<LoginResponse> {
                                    override fun onResponse(
                                        call: Call<LoginResponse>,
                                        response: Response<LoginResponse>,
                                    ) {
                                        isLoading.value = false
                                        if (response.isSuccessful) {
                                            val loginResponse = response.body()
                                            saveToken(context, loginResponse?.accessToken ?: "")
                                            navController.navigate(Routes.HOME)
                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            errorMessage.value = try {
                                                if (!errorBody.isNullOrEmpty()) {
                                                    val jsonObj = JSONObject(errorBody)
                                                    val detail = jsonObj.optJSONObject("detail")
                                                    when {
                                                        detail?.has("message") == true -> detail.getString(
                                                            "message"
                                                        )

                                                        detail?.has("detail") == true -> detail.getString(
                                                            "detail"
                                                        )

                                                        else -> "An error occurred."
                                                    }
                                                } else "An error occurred."
                                            } catch (e: Exception) {
                                                "An error occurred."
                                            }
                                            showDialog.value = true
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<LoginResponse>,
                                        t: Throwable,
                                    ) {
                                        isLoading.value = false
                                        errorMessage.value = "Failed to connect to the server"
                                        showDialog.value = true
                                    }
                                })
                        } catch (e: Exception) {
                            isLoading.value = false
                            errorMessage.value = "An error occurred: ${e.message}"
                            showDialog.value = true
                        }
                    }
                }, modifier = Modifier.fillMaxWidth(), enabled = isFormValid && !isLoading.value
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                } else {
                    Text(
                        text = "Login",
                        fontSize = 17.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            Text(
                text = "Or, login with...",
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .alpha(0.5f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                AuthOption(image = R.drawable.google)
                AuthOption(image = R.drawable.facebook)
                AuthOption(
                    image = R.drawable.apple, tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontSize = 16.sp,
                )
                Text(text = "Register",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { navController.navigate(Routes.REGISTER) })
            }

            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}