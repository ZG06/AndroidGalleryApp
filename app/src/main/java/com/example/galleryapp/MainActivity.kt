package com.example.galleryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.galleryapp.ui.theme.GalleryAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    GalleryApp()
                }
            }
        }
    }
}

@Composable
fun GalleryApp() {
    PaintingItem()
}

@Preview(showBackground = true)
@Composable
fun GalleryAppPreview() {
    // App preview
    GalleryAppTheme {
        PaintingItem()
    }
}