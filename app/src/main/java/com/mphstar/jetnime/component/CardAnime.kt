import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mphstar.jetnime.R

@Composable
fun CardAnime(
    image: Int,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(200.dp)
                .clip(
                    RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
        )
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardAnimePreview() {
    CardAnime(
        image = R.drawable.komari,
        title = "Hikikomari Kyuuketsuki"
    )
}