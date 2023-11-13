package com.example.dragmultipleboxes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dragmultipleboxes.ui.theme.DragMultipleBoxesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragMultipleBoxesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.ui.graphics.Color.Cyan
                ) {
                    //Greeting("Android")
                    val boxes = BoxView(this)
                    boxes.setBackgroundColor(android.graphics.Color.BLUE)
                    setContentView(boxes)
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DragMultipleBoxesTheme {
        Greeting("Android")
    }
}