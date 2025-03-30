package com.example.galleryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var paintingNumber by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Painting's background
        Surface(
            modifier = Modifier
                .border(width = 25.dp, color = Color.White, shape = RectangleShape)
                .shadow(12.dp)
                .fillMaxWidth()
                .heightIn(min = 500.dp)
        ) {
            // Painting
            Image(
                painter = painterResource(getResourceForPainting(paintingNumber)),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        PaintingDescriptionText(
            modifier = Modifier.padding(top = 15.dp),
            paintingNumber = paintingNumber
        )
        // 'Previous' and 'Next' buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            // 'Previous' button
            ButtonsRow(
                paintingNumber = paintingNumber,
                onPaintingNumberChange = {
                    if (it == 1) {
                        paintingNumber = 5
                    } else {
                        paintingNumber--
                    }
                },
                buttonText = "Previous"
            )
            // 'Next' button
            ButtonsRow(
                paintingNumber = paintingNumber,
                onPaintingNumberChange = {
                    if (it == 5) {
                        paintingNumber = 1
                    } else {
                        paintingNumber++
                    }
                },
                buttonText = "Next"
            )
        }
    }
}

@Composable
fun PaintingDescriptionText(
    paintingNumber: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(bottom = 15.dp)
            .background(color = Color(0xFFe4e6eb))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        // Painting's name
        Text(
            text = stringResource(getResourceForPaintingDescription(paintingNumber)),
            style = TextStyle(fontSize = 24.sp),
            color = Color.Gray,
            modifier = Modifier.padding(start = 15.dp, top = 15.dp)
        )
        Row(
            modifier = Modifier.padding(start = 15.dp, bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Painter's name
            Text(
                text = stringResource(getResourceForPainterName(paintingNumber)),
                modifier = Modifier.padding(end = 3.dp),
                style = TextStyle(fontSize = 16.sp),
                fontWeight = FontWeight.Bold
            )
            // Year painter
            Text(
                text = stringResource(getResourceForPaintingYear(paintingNumber)),
                style = TextStyle(fontSize = 14.sp),
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ButtonsRow(
    paintingNumber: Int,
    onPaintingNumberChange: (Int) -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    // A button constructor
    Button(
        onClick = { onPaintingNumberChange(paintingNumber) },
        modifier.widthIn(min = 100.dp)
    ) {
        Text(buttonText)
    }
}

fun getResourceForPainting(paintingNumber: Int): Int {
    // Gets painter resources for the paintings
    return when (paintingNumber) {
        1 -> R.drawable.painting_1
        2 -> R.drawable.painting_2
        3 -> R.drawable.painting_3
        4 -> R.drawable.painting_4
        5 -> R.drawable.painting_5
        else -> R.drawable.painting_1
    }
}

fun getResourceForPaintingDescription(paintingNumber: Int): Int {
    // Gets string resources for the paintings names
    return when (paintingNumber) {
        1 -> R.string.painting_description_1
        2 -> R.string.painting_description_2
        3 -> R.string.painting_description_3
        4 -> R.string.painting_description_4
        5 -> R.string.painting_description_5
        else -> R.string.painting_description_1
    }
}

fun getResourceForPainterName(paintingNumber: Int): Int {
    // Gets string resources for the paintings' painters' names
    return when (paintingNumber) {
        1 -> R.string.painter_name_1
        2 -> R.string.painter_name_2
        3 -> R.string.painter_name_3
        4 -> R.string.painter_name_4
        5 -> R.string.painter_name_5
        else -> R.string.painter_name_1
    }
}

fun getResourceForPaintingYear(paintingNumber: Int): Int {
    // Gets string resources for the year when the paintings were painted
    return when (paintingNumber) {
        1 -> R.string.year_painted_1
        2 -> R.string.year_painted_2
        3 -> R.string.year_painted_3
        4 -> R.string.year_painted_4
        5 -> R.string.year_painted_5
        else -> R.string.year_painted_1
    }
}

@Preview
@Composable
fun GalleryAppPreview() {
    // App preview
    GalleryAppTheme {
        GalleryApp()
    }
}