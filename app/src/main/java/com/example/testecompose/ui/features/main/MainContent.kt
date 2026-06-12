package com.example.testecompose.ui.features.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testecompose.R
import com.example.testecompose.ui.ThemeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    onNavigationToLogin: () -> Unit
) {
//    val activity = LocalActivity.current as? ComponentActivity
//        ?: return
//    val themeViewModel: ThemeViewModel =
//        viewModel(activity)

    val themeViewModel = koinViewModel<ThemeViewModel>()
    val darkTheme by themeViewModel.darkTheme.collectAsStateWithLifecycle(initialValue = false)
    MainContent(
        onNavigationToLogin,
        themeViewModel::toggleTheme,
        darkTheme
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    onNavigationToLogin: () -> Unit,
    onToggleTheme: () -> Unit,
    darkTheme: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Main")
                },
                actions = {
                    IconButton(
                        onClick = {
                            onToggleTheme()
                        }
                    ) {
                        val (icon, description) = if (darkTheme) {
                            Icons.Rounded.LightMode to "Tema claro"
                        } else {
                            Icons.Rounded.DarkMode to "Tema escuro"
                        }
                        Icon(
                            imageVector = icon,
                            contentDescription = description
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .width(250.dp)
                    .clip(
                        shape = RoundedCornerShape(16.dp)
                    ),
                painter = painterResource(R.drawable.img),
                contentDescription = "Imagem da Pomni",
                contentScale = ContentScale.None,
                colorFilter = ColorFilter.colorMatrix(
                    colorMatrix = ColorMatrix().apply {
                        setToSaturation(1.7f)
                        setToRotateRed(1f)
                        setToRotateGreen(2f)
                    }
                )
            )

            Button(
                onClick = {
                    onNavigationToLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(64.dp, 16.dp)
            ) {
                Text("Voltar")
            }
        }
    }
}

@Preview
@Composable
private fun MainPreview() {
    MainContent(
        onNavigationToLogin = {},
        onToggleTheme = {},
        darkTheme = false
    )
}