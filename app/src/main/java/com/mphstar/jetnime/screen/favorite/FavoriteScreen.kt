import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mphstar.jetnime.R
import com.mphstar.jetnime.common.UiStateFavorite
import com.mphstar.jetnime.screen.favorite.FavoriteViewModel
import com.mphstar.jetnime.ui.theme.JetnimeTheme

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    navigationToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    JetnimeTheme(statusBarColor = Color.White, isStatusBarDark = true) {
        Column(
            modifier = modifier
                .padding(
                    vertical = 16.dp,
                    horizontal = 12.dp
                )
        ) {
            Text(
                text = "List Favorite Anime",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )

            Spacer(modifier = modifier.height(20.dp))

            when (val res = viewModel.result.value) {
                is UiStateFavorite.Loading -> {
                    viewModel.getData()
                }

                is UiStateFavorite.Available -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(res.data, key = { it.id }
                        ) { nime ->
                            CardFavorite(
                                anime = nime,
                                modifier = modifier
                                    .clickable {
                                        navigationToDetail(nime.id)
                                    },
                                handleDelete = {
                                    viewModel.removeFavorite(nime)
                                }
                            )
                        }
                    }
                }

                is UiStateFavorite.Empty -> {
                    Box(
                        modifier = modifier
                            .padding(PaddingValues(16.dp))
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .fillMaxSize()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.nodata
                                ),
                                contentDescription = "No Data",
                                modifier = modifier
                                    .size(200.dp)
                            )
                            Text(
                                text = "Tidak ada data",
                                textAlign = TextAlign.Center,
                                modifier = modifier
                                    .padding(
                                        horizontal = 8.dp
                                    )
                            )
                        }

                    }
                }
            }
        }
    }

}