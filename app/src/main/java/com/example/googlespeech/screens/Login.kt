package com.example.googlespeech.screens

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.googlespeech.components.AuthOption
import com.example.googlespeech.components.TextField
import com.example.googlespeech.utils.Routes
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val emailState = remember { TextFieldState() }
    val passwordState = remember { TextFieldState() }

    fun saveToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("access_token", token).apply()
    }

    fun getAccessToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("access_token", null)
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

        TextField(
            textFieldState = emailState,
            hint = "Email",
            leadingIcon = Icons.Outlined.Email,
            trailingIcon = Icons.Outlined.Check,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth(),
            onTextChanged = { email.value = it },
        )

        TextField(textFieldState = passwordState,
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            trailingText = "Forgot?",
            isPassword = true,
            modifier = Modifier.fillMaxWidth(),
            onTextChanged = { password.value = it })

        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        val response =
                            ApiClient.loginService.androidLogin(email.value, password.value)
                                .execute()
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            Log.d("Login", "Login berhasil: ${loginResponse?.detail}")
                            loginResponse?.let { saveToken(context, it.accessToken) }
                        } else {
                            Log.e("Login", "Login gagal: ${response.errorBody()?.string()}")
                        }
                    } catch (e: Exception) {
                        Log.e("Login", "Error: ${e.message}")
                    }
                }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Login", fontSize = 17.sp, modifier = Modifier.padding(vertical = 8.dp)
            )
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