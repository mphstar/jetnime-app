import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mphstar.jetnime.R
import com.mphstar.jetnime.data.DummyDataAnime
import com.mphstar.jetnime.model.ModelAnime

@Composable
fun CardFavorite(
    anime: ModelAnime,
    handleDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = Color(0xFFF1F1F1),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(8.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row (
                modifier = modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = anime.image),
                    contentDescription = "foto",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(
                            shape = RoundedCornerShape(6.dp)
                        )
                )
                Column {
                    Text(
                        text = anime.title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = anime.description,
                        style = TextStyle(
                            fontSize = 12.sp
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Image(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete",
                colorFilter = ColorFilter.tint(Color.Red),
                modifier = modifier
                    .clickable {
                        handleDelete()
                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardFavoritePreview() {
    CardFavorite(anime = DummyDataAnime.dummyAnime.first(), handleDelete = {})
}