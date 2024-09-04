package br.com.impacta.emudetector.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.impacta.emudetector.ui.theme.EmuDetectorTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContent {
            EmuDetectorTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController, auth) }
                    composable(
                        "error/{errorType}",
                        arguments = listOf(navArgument("errorType") { type =
                            NavType.StringType })
                    ) { backStackEntry ->
                        val errorType = backStackEntry.arguments?.getString("errorType") ?: ""
                        ErrorScreen(navController, errorType)
                    }
                    composable("menu") { MenuScreen(navController, auth) }
                }
            }
        }
    }
}