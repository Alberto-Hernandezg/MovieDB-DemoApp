package com.home.presentation.compose.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.home.presentation.R
import com.shared.domain.model.Film

@Composable
fun HomeFilm(
    filmData: Film,
    onClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            )
            .clickable { onClick(filmData.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(filmData.title)
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.film_home_rating, filmData.rating),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_bookmark_24),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    onFavoriteClick(filmData.id)
                    Toast.makeText(
                        context,
                        "${filmData.title} ${if (filmData.isFav) "removed from" else "added to"} favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                },
            tint = if (filmData.isFav) Color.Blue else Color.LightGray
        )
    }
}
