package com.example.testecompose.ui.features.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FormScreen() {
    var phone by remember { mutableStateOf("") }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            TextField(
                phone,
                onValueChange = {
                    if(it.length <= 11) {
                        phone = it
                    }
                },
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                label = {
                    Text("Telefone")
                },
                visualTransformation = PhoneVisualTransformation(),
            )
        }
    }
}

class PhoneVisualTransformation : VisualTransformation {
    //(XX) XXXXX-XXXX
    override fun filter(text: AnnotatedString): TransformedText {

        val phoneMask = text.text.mapIndexed { index, c ->
            when (index) {
                0 -> "($c"
                1 -> "$c) "
                6 -> "$c-"
                else -> c
            }
        }.joinToString("")

        return TransformedText(
            AnnotatedString(phoneMask),
            PhoneOffsetMapping
        )
    }

    object PhoneOffsetMapping : OffsetMapping {

        override fun originalToTransformed(offset: Int): Int {
            return when {
                offset > 6 -> offset + 4
                offset > 1 -> offset + 3
                offset > 0 -> offset + 1
                else -> offset
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                offset >= 11 -> offset - 4
                offset >= 5 -> offset - 3
                offset == 4 -> offset - 2
                offset >= 2 -> offset - 1
                else -> offset
            }
        }

    }
}

@Preview
@Composable
private fun FormPreview() {
    FormScreen()
}