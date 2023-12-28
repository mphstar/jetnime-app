package com.mphstar.jetnime.screen.favorite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mphstar.jetnime.common.UiStateFavorite
import com.mphstar.jetnime.data.DummyDataAnime
import com.mphstar.jetnime.model.ModelAnime


class FavoriteViewModel : ViewModel() {
    val result: MutableState<UiStateFavorite> = mutableStateOf(UiStateFavorite.Empty)

    val favoriteData: ArrayList<ModelAnime> = arrayListOf()

    fun getData() {
        if(favoriteData.isEmpty()){
            result.value = UiStateFavorite.Empty
        } else {
            result.value = UiStateFavorite.Available(favoriteData)
        }

    }

    fun addFavorite(anime: ModelAnime){
        favoriteData.add(anime)

        getData()
    }

    fun removeFavorite(anime: ModelAnime){
        favoriteData.remove(anime)

        getData()
    }

}