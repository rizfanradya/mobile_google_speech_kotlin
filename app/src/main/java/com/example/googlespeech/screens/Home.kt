package com.example.googlespeech.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.googlespeech.components.UserGuard
import com.example.googlespeech.ui.theme.Purple80
import com.example.googlespeech.R
import androidx.core.graphics.toColorInt

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    UserGuard(navController) {
        val message = remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
                .background(color = Color("#f2f1f6".toColorInt())),
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Google Speech",
                    color = Color.White,
                    fontSize = 22.sp
                )
            }

            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.baseline_question_answer_24),
                    contentDescription = "Icon",
                    tint = Purple80,
                )
                Text(text = "Ask me anything", fontSize = 22.sp)
            }

            Row(
                modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f), value = message.value, onValueChange = {
                        message.value = it
                    })
                IconButton(onClick = {
                    if (message.value.isNotEmpty()) {
                        message.value = ""
                    }
                }, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = Icons.Default.Send, contentDescription = "Send"
                    )
                }
            }
        }
    }
}