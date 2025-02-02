package com.esmanureral.filmjc
import android.view.View
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.esmanureral.filmjc.ui.navigation.MovieNavigation
import com.esmanureral.filmjc.ui.theme.FilmJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Edge to edge ekran özelliğini aktifleştir
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        setContent {
            MoviePage {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MoviePage(mainContent: @Composable () -> Unit) {
    FilmJCTheme {
        mainContent()
    }
}

@Preview(showBackground = true)
@Composable
fun MoviePagePreview() {
    MoviePage {
        MovieNavigation()
    }
}
