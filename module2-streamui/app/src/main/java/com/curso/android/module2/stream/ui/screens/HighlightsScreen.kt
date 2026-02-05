package com.curso.android.module2.stream.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.curso.android.module2.stream.ui.navigation.HighlightsDestination
import com.curso.android.module2.stream.ui.viewmodel.HomeViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.curso.android.module2.stream.data.model.Song
import com.curso.android.module2.stream.ui.components.SongCard
import com.curso.android.module2.stream.ui.viewmodel.HomeUiState


@Composable
fun HighlightsScreen(
    viewModel: HomeViewModel,
    onSongClick: (Song) -> Unit,
    modifier: Modifier = Modifier
){
    val uiState by viewModel.uiState.collectAsState()

    //un contenedor unico para toda la pantalla
    Box(modifier = modifier.fillMaxSize()){
        when(val state = uiState){
            is HomeUiState.Loading ->{
                LoadingContent()
            }

            is HomeUiState.Error -> {
                ErrorContent(message=state.message)
            }

            is HomeUiState.Success ->{
                HighlightsContent(
                    //para obtener la lista de favoritas
                    favorites = state.categories
                        .flatMap { it.songs }
                        .filter { it.isFavorite },
                        onSongClick = onSongClick,
                    //evento que se dispara al tocar el ícono de favorito. el viewModel se encarga de la actualizacion del estado
                        onFavoriteClick = {id->viewModel.toggleFavorite(id)}
                )
            }
        }
    }

}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $message",
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun HighlightsContent(
    favorites: List<Song>,
    onSongClick: (Song) -> Unit,
    onFavoriteClick: (String) -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 18.dp)
    ) {

        Spacer(Modifier.height(12.dp))

        if(favorites.isEmpty()){
            Text(
                text = "No hay canciones favoritas",
                modifier = Modifier.padding(horizontal = 18.dp)
            )
        }else{
            //LazyVerticalGrid para tener varias filas y columnas en este caso el scroll es hacia abajo
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                //por cada cancion favorita se crea un songcard
                items(items = favorites, key = { it.id }) { song ->
                    SongCard(
                        song = song,
                        onClick = { onSongClick(song) },
                        onFavoriteClick = onFavoriteClick
                    )
                }
            }
        }
    }
}