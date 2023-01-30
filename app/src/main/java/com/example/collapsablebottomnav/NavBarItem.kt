package com.example.collapsablebottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavBarItem(val route: String, val title: String, val icon: ImageVector){
    object Home: NavBarItem("home", "Home", Icons.Filled.Home)
    object Categories: NavBarItem("categories", "Categories", Icons.Filled.List)
    object Cart: NavBarItem("cart", "Cart", Icons.Default.ShoppingCart)
    object Account: NavBarItem("account", "Account", Icons.Filled.Person)
}
