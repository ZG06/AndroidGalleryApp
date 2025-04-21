package com.example.galleryapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private const val NO_OF_PAINTINGS = 10

@Composable
fun PaintingItem(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    var paintingNumber by remember { mutableIntStateOf(1) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(
                top = 60.dp,
                bottom = 40.dp,
                start = 40.dp,
                end = 40.dp
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Painting(getDrawableResourceByNumber(
            paintingNumber = paintingNumber,
            context = context,
        ))
        Spacer(modifier = Modifier.height(15.dp))
        PaintingDescriptionText(
            modifier = Modifier.padding(top = 15.dp),
            paintingDescription = getStringResourceByNumber(
                resource = "painting_description",
                paintingNumber = paintingNumber,
                context = context
            ),
            paintingName = getStringResourceByNumber(
                resource = "painter_name",
                paintingNumber = paintingNumber,
                context = context
            ),
            paintingYear = getStringResourceByNumber(
                resource = "year_painted",
                paintingNumber = paintingNumber,
                context = context
            ),
        )
        // Moving the buttons to the bottom of the app
        Spacer(modifier = Modifier.weight(1f))
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
                        paintingNumber = NO_OF_PAINTINGS
                    } else {
                        paintingNumber--
                    }
                },
                buttonText = stringResource(R.string.previous_button)
            )
            // 'Next' button
            ButtonsRow(
                paintingNumber = paintingNumber,
                onPaintingNumberChange = {
                    if (it == NO_OF_PAINTINGS) {
                        paintingNumber = 1
                    } else {
                        paintingNumber++
                    }
                },
                buttonText = stringResource(R.string.next_button)
            )
        }
    }
}

@Composable
fun Painting(
    @DrawableRes painting: Int,
    modifier: Modifier = Modifier
) {
    // Painting's background
    Surface(
        modifier = modifier
            .border(width = 25.dp, color = Color.White, shape = RectangleShape)
            .shadow(12.dp)
            .fillMaxWidth()
            .heightIn(min = 500.dp)
            .padding(top = 30.dp)
    ) {
        // Painting
        Image(
            painter = painterResource(painting),
            contentDescription = null
        )
    }
}

@Composable
fun PaintingDescriptionText(
    @StringRes paintingDescription: Int,
    @StringRes paintingName: Int,
    @StringRes paintingYear: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(bottom = 15.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        // Painting's name
        Text(
            text = stringResource(paintingDescription),
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
                text = stringResource(paintingName),
                modifier = Modifier.padding(end = 3.dp),
                style = TextStyle(fontSize = 16.sp),
                fontWeight = FontWeight.Bold
            )
            // Year painter
            Text(
                text = stringResource(paintingYear),
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

@SuppressLint("DiscouragedApi")
fun getStringResourceByNumber(resource: String, paintingNumber: Int, context: Context): Int {
    val resourceName = "${resource}_${paintingNumber}"
    val resourceId = context.resources.getIdentifier(resourceName, "string", context.packageName)
    return resourceId
}

@SuppressLint("DiscouragedApi")
fun getDrawableResourceByNumber(paintingNumber: Int, context: Context): Int {
    val resourceName = "painting_$paintingNumber"
    val resourceId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    return resourceId
}