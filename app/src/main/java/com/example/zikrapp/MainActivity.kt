package com.example.zikrapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.zikrapp.ui.screens.ZikrApp
import com.example.zikrapp.ui.theme.ZikrAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContent {
            ZikrAppTheme {

                ZikrApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ZikrAppTheme {
        ZikrApp()
    }
}