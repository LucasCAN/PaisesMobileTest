package com.example.paisestestemobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.paisestestemobile.composables.CountryScreen
import com.example.paisestestemobile.composables.SearchScreen
import com.example.paisestestemobile.data.countries.CountriesViewModel
import com.example.paisestestemobile.navigation.Screen
import com.example.paisestestemobile.ui.theme.PaisesTesteMobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaisesTesteMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CountryApp()
                }
            }
        }
    }
}



@Composable
fun CountryApp(
    countriesViewModel: CountriesViewModel = hiltViewModel()
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SEARCH_SCREEN.name) {
        composable(Screen.SEARCH_SCREEN.name) {
            SearchScreen(navController, countriesViewModel)
        }
        composable(Screen.COUNTRY_SCREEN.name) {
            CountryScreen(navController, countriesViewModel)
        }
    }
}