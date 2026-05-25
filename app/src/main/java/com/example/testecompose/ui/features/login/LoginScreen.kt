package com.example.testecompose.ui.features.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.mutableListOf

@Composable
fun LoginScreen(onNavigationToMain: (user: String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val users = remember { mutableStateListOf<String>() }
    var refreshUsers by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    LaunchedEffect(refreshUsers) {
        users.addAll(testCoroutines())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login Page",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )

        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Preencha os campos!", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                Toast.makeText(context, "Login efetuado com sucesso.", Toast.LENGTH_SHORT).show()
                onNavigationToMain("usuario teste")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        ) {
            Text("Login")
        }
    }
}

private suspend fun testCoroutines(): List<String> = withContext(IO) {
    Log.i("testCoroutines", "thread ${Thread.currentThread()}")
    delay(4300)
    listOf(
        "arroz",
        "feijao",
        "batata"
    )
}

@Preview
@Composable
private fun LoginPreview() {
    LoginScreen(onNavigationToMain = {})
}