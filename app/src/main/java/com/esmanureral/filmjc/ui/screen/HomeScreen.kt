package com.esmanureral.filmjc.ui.screen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esmanureral.filmjc.data.model.MovieItem
import com.esmanureral.filmjc.data.model.getDummyMovie
import com.esmanureral.filmjc.ui.components.ComingSoonBanner
import com.esmanureral.filmjc.ui.components.MovieCircleImage
import com.esmanureral.filmjc.ui.navigation.MovieScreens
import com.esmanureral.filmjc.ui.theme.Color2Beige
import com.esmanureral.filmjc.ui.theme.Color2Blue
import com.esmanureral.filmjc.ui.theme.Color2Green
import com.esmanureral.filmjc.ui.theme.Color2Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },  // Başlık çubuğu
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color2Beige,
                    titleContentColor = Color.Black
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color2Beige)
                .padding(it)
        ) {
            MovieContent(
                navController = navController  // Film içeriğini göster
            )
        }
    }
}

@Composable
fun MovieContent(
    navController: NavController,
    movieList: ArrayList<MovieItem> = getDummyMovie()  // Dummy verilerle film listesi
) {
    LazyColumn {
        items(movieList) {
            MovieRow(movie = it) { movie ->  // Her bir film için satır oluştur
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movie")  // Detay ekranına yönlendir
            }
        }
    }
}

@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movie: MovieItem,
    onItemClick: (String) -> Unit = {}  // Film tıklama fonksiyonu
) {

    Card(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.movieImdbID)  // Film tıklandığında detay ekranına git
            },
        colors = CardDefaults.cardColors(
            containerColor = Color2Blue,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            MovieRowPosterImage(movie = movie)  // Film posterini göster

            Column(
                modifier = modifier.padding(10.dp)
            ) {
                MovieRowMainData(movie)  // Ana film verilerini göster
                MovieRowExpendableData(movie = movie)  // Detaylı film verilerini aç/kapa
            }
        }
    }
}

@Composable
private fun MovieRowPosterImage(
    modifier: Modifier = Modifier,
    movie: MovieItem
) {
    Surface(
        modifier = modifier
            .padding(10.dp)
            .size(100.dp),
        shape = CircleShape,
        shadowElevation = 10.dp
    ) {
        MovieCircleImage(imageUrl = movie.movieImages.first())  // Film posterini dairesel göster
        if (movie.movieComingSoon) {
            ComingSoonBanner(imageUrl = "https://cdn-icons-png.flaticon.com/128/8089/8089442.png")  // Eğer "Coming Soon" ise banner göster
        }
    }
}

@Composable
private fun MovieRowMainData(movie: MovieItem) {
    Text(
        text = movie.movieTitle,
        style = MaterialTheme.typography.titleMedium  // Film başlığını göster
    )
    Text(
        text = "Director: ${movie.movieDirection}",
        style = MaterialTheme.typography.labelMedium  // Yönetmeni göster
    )
    Text(
        text = "Date: ${movie.movieYear}",
        style = MaterialTheme.typography.labelMedium  // Yayın yılı bilgisini göster
    )
}

@Composable
private fun MovieRowExpendableData(modifier: Modifier = Modifier, movie: MovieItem) {
    var expandableState by remember { mutableStateOf(false) }  // Detayların açılıp kapanmasını kontrol eden durum

    AnimatedVisibility(visible = expandableState) {
        Column {
            Divider(
                modifier = modifier
                    .padding(vertical = 5.dp)
                    .border(0.4.dp, Color.Black)
            )
            Text(
                text = "${movie.moviePlot}",
                style = MaterialTheme.typography.bodySmall  // Film konusu
            )
            Spacer(modifier = modifier.padding(vertical = 3.dp))
            Text(
                text = "Actors: ${movie.movieActors}",
                style = MaterialTheme.typography.bodySmall  // Film oyuncuları
            )
            Spacer(modifier = modifier.padding(vertical = 4.dp))
            Text(
                text = "Rating: ${movie.movieImdbRating}",
                style = MaterialTheme.typography.bodySmall  // Film puanı
            )
        }
    }

    Surface(
        modifier = modifier
            .clip(CircleShape)
            .padding(top = 10.dp)
            .border(1.dp, Color2Pink, CircleShape),
        color = Color2Green,
        shape = CircleShape,
    ) {
        Icon(
            imageVector = if (expandableState) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            modifier = modifier
                .size(25.dp)
                .clickable {
                    expandableState = !expandableState  // Detaylar açılacaksa aç, kapanacaksa kapa
                },
            tint = Color2Beige,
            contentDescription = "Down Arrow"  // Ok simgesi açıklaması
        )
    }
}
