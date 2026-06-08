package com.example.testecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.testecompose.ui.features.form.FormScreen
import com.example.testecompose.ui.navigation.AppNavHost
import com.example.testecompose.ui.theme.TesteComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TesteComposeTheme {
//                AppNavHost(rememberNavController())
                FormScreen()
            }
        }
    }
}

@Composable
fun HomeRowColumns() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Surface(
            modifier = Modifier.size(100.dp),
            color = Color.Green
        ) {}

        Surface(
            modifier = Modifier.size(100.dp),
            color = Color.Blue
        ) {}

        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp),
                color = Color.Green
            ) {

            }
        }
    }
}

@Composable
fun HomeCanvas() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(Color.White)
        ) {
            drawRect(
                color = Color.Green,
                size = Size(
                    width = 250f,
                    height = 250f
                ),
                topLeft = Offset(
                    x = 50f,
                    y = 50f
                )
            )

            drawOval(
                color = Color.Green,
                size = Size(
                    width = 150f,
                    height = 150f
                ),
                topLeft = Offset(
                    x = 50f,
                    y = 500f
                )
            )

            drawLine(
                color = Color.Green,
                start = Offset(
                    x = 500f,
                    y = 500f
                ),
                end = Offset(
                    x = 700f,
                    y = 700f
                ),
                strokeWidth = 8f
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffold() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Teste")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue
                ),
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = null
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Blue
            ) {

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Teste1",
                fontSize = 20.sp
            )
            Text(
                text = "Teste2",
                fontSize = 20.sp
            )
            Text(
                text = "Teste3",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun HomeBox() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Black)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Green)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color.Red)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeBoxPreview() {
    HomeBox()
}


