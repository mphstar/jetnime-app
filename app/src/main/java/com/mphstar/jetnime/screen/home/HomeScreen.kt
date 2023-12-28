package com.mphstar.jetnime.screen.home

import CardAnime
import FormSearch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.mphstar.jetnime.common.UiStateHome
import com.mphstar.jetnime.data.DummyDataAnime
import com.mphstar.jetnime.model.ModelAnime
import com.mphstar.jetnime.navigation.Screen
import com.mphstar.jetnime.ui.theme.JetnimeTheme
import com.mphstar.jetnime.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LoadData(
        viewModel = viewModel,
        navigateToDetail = navigateToDetail
    )
}

@Composable
fun LayoutHome(
    valueSearch: String,
    onValueSearchChange: (String) -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    JetnimeTheme (
        statusBarColor = Color.Blue,
        isStatusBarDark = false
    ) {
        Box(
            modifier = modifier
                .background(Color(0xFFF1F7FD))
        ) {
            Box(
                modifier = modifier
                    .background(
                        color = Color.Blue,
                        shape = RoundedCornerShape(
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    )
                    .fillMaxWidth()
                    .height(200.dp)

            ) {

            }
            Column(
                modifier = modifier
                    .imePadding()
            ) {
                Spacer(modifier = modifier.height(10.dp))



                FormSearch(
                    value = valueSearch,
                    onValueChange = onValueSearchChange,
                    modifier = modifier
                        .padding(
                            horizontal = 10.dp
                        )
                )

                // isi konten
                content()
                // End isi konten
            }
        }
    }
}

@Composable
private fun LoadData(
    viewModel: HomeViewModel,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }

    when (val result = viewModel.result.value) {

        is UiStateHome.Loading -> {
            viewModel.getData()
            LayoutHome(
                valueSearch = searchValue,
                onValueSearchChange = { newValue ->
                    searchValue = newValue
                    if (searchValue != "") {
                        viewModel.searchData(newValue)
                    } else {
                        viewModel.getData()
                    }
                },
                content = {
                    Box(
                        modifier = modifier
                            .padding(PaddingValues(16.dp))
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                            Text(
                                text = "Loading Data",
                                modifier = modifier.padding(vertical = 10.dp)
                            )
                        }

                    }
                }
            )

        }

        is UiStateHome.Error -> {

        }

        is UiStateHome.Success -> {
            LayoutHome(
                valueSearch = searchValue,
                onValueSearchChange = { newValue ->
                    searchValue = newValue
                    if (searchValue != "") {
                        viewModel.searchData(newValue)
                    } else {
                        viewModel.getData()
                    }
                },
                content = {
                    if (result.data.isEmpty()) {
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
                                Image(painter = painterResource(
                                    id = R.drawable.nodata),
                                    contentDescription = "No Data",
                                    modifier = modifier
                                        .size(200.dp)
                                )
                                Text(
                                    text = "Pencarian \"${searchValue}\" Tidak ditemukan",
                                    textAlign = TextAlign.Center,
                                    modifier = modifier
                                        .padding(
                                            horizontal = 8.dp
                                        )
                                )
                            }

                        }
                    } else {
                        HomeContent(
                            navigateToDetail = navigateToDetail,
                            anime = result.data
                        )
                    }
                },
            )
        }

    }

}

private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(2) }

@Composable
fun HomeContent(
    anime: List<ModelAnime>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(PaddingValues(16.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxSize()
    ) {
        item(
            span = span
        ) {
            Text(
                text = "Anime List",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        items(
            anime,
            key = { it.id }
        ) { data ->
            CardAnime(
                image = data.image,
                title = data.title,
                modifier = modifier
                    .clickable {
                        navigateToDetail(data.id)
                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeContent(
        navigateToDetail = {},
        anime = DummyDataAnime.dummyAnime
    )
}