package com.esmanureral.filmjc.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esmanureral.filmjc.data.model.MovieItem
import com.esmanureral.filmjc.data.model.getDummyMovie
import com.esmanureral.filmjc.ui.components.MovieRectangleImage
import com.esmanureral.filmjc.ui.theme.Color2Beige
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(modifier: Modifier = Modifier,
                 navController: NavController,
                 movieId: String?
) {
    val context = LocalContext.current
    val detailedMovie = getDummyMovie().first { it.movieImdbID == movieId }  // Seçilen film verisini al

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = detailedMovie.movieTitle) },  // Başlık çubuğunda filmin başlığını göster
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color2Beige,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {  // Geri gitme butonu
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            tint = Color.Black,
                            contentDescription = "Detail Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "${detailedMovie.movieTitle} added to favorites.", Toast.LENGTH_SHORT).show()  // Favorilere ekleme işlemi
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            tint = Color.Red,
                            contentDescription = "Favorite"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color2Beige)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MainContent(movie = detailedMovie)  // Filmin detaylarını ana içerikte göster
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, movie: MovieItem) {
    MovieDetailData(movie, modifier)  // Film verilerini göster
    MovieDetailScrollableImage(movie, modifier)  // Film resimlerini kaydırılabilir şekilde göster
}

@Composable
private fun MovieDetailData(
    movie: MovieItem,
    modifier: Modifier
) {
    Text(
        text = movie.movieTitle,
        style = MaterialTheme.typography.headlineLarge  // Başlık olarak film adını göster
    )
    Text(
        text = movie.movieYear,
        style = MaterialTheme.typography.titleLarge  // Yayın yılı
    )
    Text(
        text = movie.movieDirection,
        style = MaterialTheme.typography.titleLarge  // Yönetmen
    )
    Text(
        text = movie.movieGenre,
        style = MaterialTheme.typography.titleMedium  // Tür
    )
    Text(
        text = movie.movieImdbRating,
        style = MaterialTheme.typography.titleMedium  // IMDb puanı
    )
    Text(
        text = movie.moviePlot,
        modifier = modifier.padding(10.dp),
        style = MaterialTheme.typography.bodyMedium  // Film konusu
    )
}

@Composable
private fun MovieDetailScrollableImage(
    movie: MovieItem,
    modifier: Modifier
) {
    LazyRow {
        items(movie.movieImages) { image ->  // Film resimlerini yatay kaydırılabilir şekilde listele
            ElevatedCard(
                modifier = modifier
                    .wrapContentSize()
                    .width(LocalConfiguration.current.screenWidthDp.dp)
                    .padding(10.dp),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                MovieRectangleImage(image)  // Her bir resmi göster
            }
        }
    }
}
