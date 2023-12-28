import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mphstar.jetnime.ui.theme.JetnimeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSearch(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Image(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        singleLine = true,
        placeholder = {
            Text(text = "Cari disini")
        },
        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),

        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .border(BorderStroke(0.dp, Color.Transparent))
            .padding(PaddingValues(6.dp))
    )
}

@Composable
@Preview(showBackground = false)
fun FormSearchPreview(){
    FormSearch(
        value = "",
        onValueChange = {}
    )
}