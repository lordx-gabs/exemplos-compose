package com.example.testecompose.ui.features.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.testecompose.R

@Composable
fun MainScreen(onNavigationToLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
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
                .padding(64.dp, 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray
            )
        ) {
            Text("Voltar")
        }
    }
}

@Preview
@Composable
private fun MainPreview() {
    MainScreen(onNavigationToLogin = {})
}