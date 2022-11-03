package com.fansymasters.shoppinglist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fansymasters.shoppinglist.ui.theme.SPACING_M

@Composable
internal fun FancyTopBar(text: String, onBackClick: (() -> Unit)?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SPACING_M.dp)
    ) {
        if (onBackClick != null) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable(onClick = onBackClick)
            )
        }
        val spacerWeight = if (onBackClick != null) 0.9f else 1f
        Spacer(modifier = Modifier.weight(spacerWeight))
        Text(text = text, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
    }
}