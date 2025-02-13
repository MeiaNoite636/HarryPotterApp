package com.example.harrypotterapp.navigation.bottom_bar_graph

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.harrypotterapp.R

sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val route: String,
    val hasNews: Boolean
) {
//    data object Home : BottomNavItem(
//        title = R.string.text_bottom_nav_home,
//        selectedIcon = R.drawable.ic_home_filled,
//        unselectedIcon = R.drawable.ic_home_line,
//        route = "home_screen",
//        hasNews = false
//    )
//
//    data object Search : BottomNavItem(
//        title = R.string.text_bottom_nav_home,
//        selectedIcon = R.drawable.ic_home_filled,
//        unselectedIcon = R.drawable.ic_home_line,
//        route = "home_screen",
//        hasNews = false
//    )
//
//    data object Like : BottomNavItem(
//        title = R.string.text_bottom_nav_like,
//        selectedIcon = R.drawable.ic_favorite_filled,
//        unselectedIcon = R.drawable.ic_favorite_line,
//        route = "favorite_screen",
//        hasNews = true
//    )
//
//    data object Profile : BottomNavItem(
//        title = R.string.text_bottom_nav_profile,
//        selectedIcon = R.drawable.ic_user_filled,
//        unselectedIcon = R.drawable.ic_user_line,
//        route = "profile_screen",
//        hasNews = false
//    )
}