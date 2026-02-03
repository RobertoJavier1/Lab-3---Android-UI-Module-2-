package com.curso.android.module2.stream.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.curso.android.module2.stream.data.model.Song

@Composable
fun SongCard(
    song: Song,
    onClick: () -> Unit,
    onFavoriteClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            // clickable hace que toda la columna sea interactiva
            // También añade feedback visual (ripple effect)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //box para superponer el icono del corazon encima del cover
        Box{
            // Cover generado por código
            SongCoverMock(
                colorSeed = song.colorSeed,
                size = 120.dp
            )

            IconButton(
                onClick={onFavoriteClick(song.id)},
                modifier = Modifier.align(Alignment.TopEnd)//para ponerlo arriba a la derecha
            ) {
                Icon(
                    imageVector = if(song.isFavorite)
                        Icons.Filled.Favorite
                    else
                        Icons.Outlined.FavoriteBorder, contentDescription = "Toggle favorite"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Título de la canción
        Text(
            text = song.title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis, // "..." si el texto es muy largo
            modifier = Modifier.fillMaxWidth()
        )

        // Artista
        Text(
            text = song.artist,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}