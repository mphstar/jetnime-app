package com.mphstar.jetnime.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
