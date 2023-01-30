package com.example.collapsablebottomnav

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlin.math.roundToInt

val bottomNavigationItems = listOf(
    NavBarItem.Home,
    NavBarItem.Categories,
    NavBarItem.Cart,
    NavBarItem.Account,
)

@Composable
fun BottomScreenNavHost(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavBarItem.Home.route) {
        composable(NavBarItem.Home.route) {
            HomeScreen(innerPadding)
        }
        composable(NavBarItem.Categories.route) {
            CategoriesScreen()
        }
        composable(NavBarItem.Cart.route) {
            CartScreen()
        }
        composable(NavBarItem.Account.route) {
            AccountScreen()
        }
    }
}

@Composable
fun BottomScreenNavController(navController: NavHostController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                selectedContentColor = Color.Magenta,
                alwaysShowLabel = true,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title
                        )
                        if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                            Text(
                                text = screen.title,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,

                // handle bottom bar items onClick
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re selecting the same item
                        launchSingleTop = true
                        // Restore state when re selecting a previously selected item
                        restoreState = true
                    }
                })
        }
    }
}
@Composable
fun BottomCollapse() {
    val bottomBarHeight = 55.dp
    val bottomBarHeightPx = with(LocalDensity.current) {
        bottomBarHeight.roundToPx().toFloat()
    }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value =
                    newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Collapse Bottom Navigation",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                })
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(bottomBarHeight)
                    .offset {
                        IntOffset(
                            x = 0,
                            y = -bottomBarOffsetHeightPx.value.roundToInt())
                    }) {
                BottomScreenNavController(navController)
            }
        }) { innerPadding ->
        BottomScreenNavHost(innerPadding, navController)
    }
}