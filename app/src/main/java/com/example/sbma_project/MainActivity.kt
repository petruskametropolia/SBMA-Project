package com.example.sbma_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.sbma_project.ui.theme.SBMAProjectTheme

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBMAProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Using AppNavigation composable
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    // Replace with your HomeScreen content
    Text("Home Screen Content")
}

@Composable
fun ExploreScreen() {
    // Replace with your ExploreScreen content
    Text("Explore Screen Content")
}

@Composable
fun ProfileScreen() {
    // Replace with your ProfileScreen content
    Text("Profile Screen Content")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("explore") {
            ExploreScreen()
        }
        composable("profile") {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        val items = listOf("Home", "Explore", "Profile")

        val currentRoute = currentRoute(navController)

        items.forEach { screen ->
            BottomNavigationItem(
                icon = { /* Icon for each tab */ },
                label = { Text(text = screen) },
                selected = currentRoute == screen,
                onClick = {
                    navController.navigate(screen) {
                        // Optionally, you can specify navigation options here
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SBMAProjectTheme {
        // Replace with your preview content
        Text("Preview Content")
    }
}
