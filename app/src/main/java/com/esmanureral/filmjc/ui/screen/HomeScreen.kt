package com.esmanureral.filmjc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esmanureral.filmjc.data.model.MovieItem
import com.esmanureral.filmjc.data.model.getDummyMovie
import com.esmanureral.filmjc.ui.components.MovieCircleImage
import com.esmanureral.filmjc.ui.components.MovieComingSoonImage
import com.esmanureral.filmjc.ui.theme.Color2Beige


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },
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
                navController = navController
            )
        }
    }
}
//film listelenmesi
@Composable
fun MovieContent(
    navController: NavController,
    movieList: ArrayList<MovieItem> = getDummyMovie()
) {

    LazyColumn {//dikey liste
        items(movieList) { movieItem -> //movieList içindeki her bir öğeyi alarak MovieRow bileşenine gönderir.
            MovieRow(movieItem = movieItem){

            }
        }
    }
}
@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movieItem: MovieItem= getDummyMovie()[0],
    onItemClick:(String)->Unit={}
) {
    Card(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movieItem.movieImdbID)
            },
        colors=CardDefaults.cardColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        elevation = CardDefaults.cardElevation(5.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            MovieRowPosterImage(movieItem=movieItem)

            Column(
                modifier=modifier.padding(10.dp)
            ) {
                MovieRowMainData(movieItem=movieItem)
            }
        }


    }

}
@Composable
fun MovieRowPosterImage(
    modifier: Modifier = Modifier,
    movieItem: MovieItem

) {
    Surface(
        modifier = modifier
            .padding(10.dp)
            .size(100.dp),
        shape = CircleShape,
        shadowElevation = 10.dp
    ) {
        Column {
            MovieCircleImage(imageUrl = movieItem.movieImages.first())
            if (movieItem.movieComingSoon) {
                MovieComingSoonImage()
            }
        }
    }
}

@Composable
fun MovieRowMainData(movieItem: MovieItem) {
    Text(
        text=movieItem.movieTitle,
        style=MaterialTheme.typography.titleMedium)
      Text(
          text="Director:${movieItem.movieDirection}",
        style=MaterialTheme.typography.labelMedium)
      Text(text="Date: ${movieItem.movieYear}",
        style=MaterialTheme.typography.labelMedium)

}