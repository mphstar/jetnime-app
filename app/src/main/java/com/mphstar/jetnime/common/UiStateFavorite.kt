package com.mphstar.jetnime.common

import com.mphstar.jetnime.model.ModelAnime

sealed class UiStateFavorite {
    class Available(val data: List<ModelAnime>) : UiStateFavorite()
    object Empty : UiStateFavorite()
    object Loading : UiStateFavorite()
}