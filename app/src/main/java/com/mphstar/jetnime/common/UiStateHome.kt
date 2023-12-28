package com.mphstar.jetnime.common

import com.mphstar.jetnime.model.ModelAnime

sealed class UiStateHome {
    class Success(val data: List<ModelAnime>) : UiStateHome()
    class Error(val message: String) : UiStateHome()
    object Loading : UiStateHome()
}