package com.mphstar.jetnime.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import com.mphstar.jetnime.common.UiStateHome
import com.mphstar.jetnime.data.DummyDataAnime
import com.mphstar.jetnime.model.ModelAnime
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val result: MutableState<UiStateHome> = mutableStateOf(UiStateHome.Loading)

    fun getData() {
        result.value = UiStateHome.Loading
        //  Delay 3 detik untuk loading, agar seakan akan seperti mengambil data dari internet
        GlobalScope.launch {
            delay(1500)

            // Setelah loading selesai, baru load data
            result.value = UiStateHome.Success(DummyDataAnime.dummyAnime)
        }
    }

    fun searchData(query: String) {
        var temp: List<ModelAnime> = DummyDataAnime.dummyAnime.filter { anime ->
            anime.title.lowercase().contains(query.lowercase())
        }

        result.value = UiStateHome.Success(temp)


    }

}