import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mphstar.jetnime.common.UiStateFavorite
import com.mphstar.jetnime.common.UiStateHome
import com.mphstar.jetnime.data.DummyDataAnime
import com.mphstar.jetnime.model.ModelAnime
import com.mphstar.jetnime.screen.favorite.FavoriteViewModel
import com.mphstar.jetnime.screen.home.HomeViewModel
import com.mphstar.jetnime.ui.theme.JetnimeTheme

@Composable
fun ImageAnime(
    anime: ModelAnime,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(
            id = anime.image
        ),
        contentDescription = anime.title,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()

    )
}

@Composable
fun JudulAnime(
    anime: ModelAnime,
    modifier: Modifier = Modifier
) {
    Text(
        text = anime.title,
        style = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier
            .padding(horizontal = 12.dp)
    )
}

@Composable
fun GenreAnime(
    anime: ModelAnime,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Mapping genre
        anime.genre.map { genre ->
            Box(
                modifier = modifier
                    .border(1.dp, Color(0xFF484848), RoundedCornerShape(6.dp))
            ) {
                Text(
                    text = genre,
                    style = TextStyle(
                        fontSize = 11.sp,
                        color = Color(0xFF484848)
                    ),
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ScoreAnime(
    anime: ModelAnime,
    modifier: Modifier = Modifier
) {
// Score
    Row(
        modifier = modifier
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Score",
            modifier = modifier,
            colorFilter = ColorFilter.tint(Color(0xFFFFC800))
        )
        Text(
            text = anime.score.toString(),
            style = TextStyle(
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun AddToFavorite(
    viewModelFavorite: FavoriteViewModel,
    anime: ModelAnime,
    modifier: Modifier = Modifier
) {

    when (val res = viewModelFavorite.result.value){
        is UiStateFavorite.Available -> {
            if(res.data.contains(anime)){
                // Cek jika sudah ada di favorite
                Button(
                    onClick = {
                        viewModelFavorite.removeFavorite(anime)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE91E63)
                    ),
                    modifier = modifier
                        .padding(horizontal = 12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Image(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Remove",
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Text(text = "Remove from Favorite")
                    }
                }
            } else {
                // Jika tidak ada
                Button(
                    onClick = {
                        viewModelFavorite.addFavorite(anime)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    ),
                    modifier = modifier
                        .padding(horizontal = 12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Image(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add",
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Text(text = "Add to Favorite")
                    }
                }
            }
        }

        else -> {
            Button(
                onClick = {
                    viewModelFavorite.addFavorite(anime)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                ),
                modifier = modifier
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add",
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(text = "Add to Favorite")
                }
            }
        }
    }


}

@Composable
fun DeskripsiAnime(
    anime: ModelAnime,
    modifier: Modifier = Modifier
) {
    Text(
        text = anime.description,
        style = TextStyle(
            fontSize = 15.sp,
            color = Color(0xFF484848)
        ),
        modifier = modifier
            .padding(horizontal = 12.dp)
    )
}

@Composable
fun AnimeLainnya(
    anime: ModelAnime,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {


    val animeLain = DummyDataAnime.dummyAnime.filter { filter ->
        filter.id != anime.id
    }

    if (animeLain.isNotEmpty()) {
        Text(
            text = "Anime Lainnya",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            modifier = modifier
                .padding(horizontal = 12.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            animeLain.map { data ->
                item(key = data.id) {
                    CardAnime(
                        image = data.image,
                        title = data.title,
                        modifier = modifier
                            .width(200.dp)
                            .clickable {
                                navigateToDetail(data.id)
                            }
                    )
                }
            }
        }
    }


}

@Composable
fun DetailAnimeScreen(
    animeId: Int,
    viewModel: HomeViewModel,
    navigateBack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    viewModelFavorite: FavoriteViewModel,
    modifier: Modifier = Modifier
) {
    JetnimeTheme (
        statusBarColor = Color.Blue,
        isStatusBarDark = false
    ) {
        when (val anime = viewModel.result.value) {
            is UiStateHome.Success -> {
                val selectedAnime = anime.data.filter { anime -> anime.id == animeId }.first()
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    ImageAnime(anime = selectedAnime)
                    Spacer(modifier = modifier.height(10.dp))
                    JudulAnime(anime = selectedAnime)
                    GenreAnime(anime = selectedAnime)
                    Spacer(modifier = modifier.height(10.dp))
                    ScoreAnime(anime = selectedAnime)
                    Spacer(modifier = modifier.height(10.dp))
                    AddToFavorite(anime = selectedAnime, viewModelFavorite = viewModelFavorite)
                    Spacer(modifier = modifier.height(10.dp))
                    DeskripsiAnime(anime = selectedAnime)
                    Spacer(modifier = modifier.height(20.dp))
                    AnimeLainnya(anime = selectedAnime, navigateToDetail = navigateToDetail)
                }
            }

            else -> {}
        }
    }


}