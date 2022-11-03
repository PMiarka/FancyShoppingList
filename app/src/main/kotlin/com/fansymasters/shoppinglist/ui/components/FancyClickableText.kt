package com.fansymasters.shoppinglist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun FancyClickableText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
) {
    Text(
        text = text,
        textDecoration = textDecoration,
        color = color,
        modifier = modifier
            .clickable(onClick = onClick)
    )
}
