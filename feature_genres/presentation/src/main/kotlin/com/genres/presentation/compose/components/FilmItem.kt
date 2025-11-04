package com.genres.presentation.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.genres.presentation.R
import com.shared.domain.model.Film

@Composable
fun FilmItem(
    filmData: Film,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp)
            .clickable { onClick(filmData.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(filmData.title)
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.chevron_right),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}
