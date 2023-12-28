import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mphstar.jetnime.R
import com.mphstar.jetnime.ui.theme.JetnimeTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    JetnimeTheme (
        statusBarColor = Color.White,
        isStatusBarDark = true
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = modifier.height(14.dp))
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "about_me",
                modifier = modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = modifier.height(14.dp))
            Text(
                text = "Bintang Malindo Eka Putra",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            )
            Text(
                text = "bintang.simdig20@gmail.com",
                style = TextStyle(
                    fontSize = 15.sp
                )
            )

            Spacer(modifier = modifier.height(14.dp))

            Text(
                text = "Website & Mobile Developer",
                style = TextStyle(
                    fontSize = 15.sp
                )
            )
            Text(
                text = "linkedin.com/in/mphstar",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color(0xFF929292)
                )
            )

            Spacer(modifier = modifier.height(14.dp))

            val module = listOf<String>(
                "Pengenalan Jetpack Compose",
                "Konsep Dasar Compose",
                "Layout pada Compose",
                "State pada Compose",
                "Lazy Layout pada Compose",
                "Navigation pada Compose",
                "Testing pada Compose",
                "Interoperability",
                "Ujian Akhir",
                "Submission Aplikasi"
            )
            Column (
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                module.map { modul ->
                    CardItem(name = "Dicoding", tanggal = "25 Desemeber 2023 - 28 Desember 2023", deskripsi = modul)
                }
            }


        }
    }

}

@Composable
fun CardItem(
    name: String,
    tanggal: String,
    deskripsi: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                Color(0xFFE6E6E6)
            )

            .height(IntrinsicSize.Min)
            .padding(vertical = 12.dp)

    ) {
        Divider(
            color = Color.Blue,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .wrapContentHeight()
                .weight(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name, style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ), modifier = modifier.weight(1f)
                )
                Image(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = "Selesai",
                    colorFilter = ColorFilter.tint(
                        Color(
                            0xFF4CAF50
                        )
                    )
                )
            }

            Text(
                text = tanggal,
                style = TextStyle(
                    fontSize = 10.sp,
                    color = Color(0xFF6D6D6D)
                )
            )

            Spacer(modifier = modifier.height(6.dp))

            Text(
                text = deskripsi,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }

    }
}