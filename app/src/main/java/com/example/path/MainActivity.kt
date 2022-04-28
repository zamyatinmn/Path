package com.example.path

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.path.ui.theme.PathTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PathTheme {
                Surface(Modifier.fillMaxSize()) {
                    Box {
                        Greetings("Android")
                    }
                }
            }
        }
    }
}

@Composable
fun Greetings(text: String) {
    Text(text = "hello $text")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PathTheme {
        Greetings("Azaza")
    }
}