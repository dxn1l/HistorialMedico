import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.DataBase.historial
import com.example.historialmedico.Screen.AddHistorialScreen
import com.example.historialmedico.Screen.EditHistorialScreen
import com.example.historialmedico.Screen.LoginScreen
import com.example.historialmedico.Screen.MenuScreen
import com.example.historialmedico.Screen.ViewHistorialScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    val historialRepository = FirebaseHistorialRepository()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = { navController.navigate("menu") })
        }
        composable("menu") {
            MenuScreen(
                historialRepository = historialRepository,
                onAddHistorialClick = { navController.navigate("addHistorial") },
                onViewHistorialClick = { navController.navigate("viewHistorial") }
            )
        }
        composable("viewHistorial") {
            ViewHistorialScreen(
                historialRepository = historialRepository,
                onBack = { navController.navigate("menu") },
                onEditHistorialClick = { historial -> navController.navigate("editHistorial/${historial.id}") }
            )
        }
        composable("editHistorial/{historialId}") { backStackEntry ->
            val historialId = backStackEntry.arguments?.getString("historialId")
            var historial by remember { mutableStateOf<historial?>(null) }
            var errorMessage by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(historialId) {
                if (historialId != null) {
                    historialRepository.getHistorialById(
                        historialId,
                        onSuccess = { historial = it },
                        onFailure = { errorMessage = "Error: ${it.message}" }
                    )
                }
            }

            if (historial != null) {
                EditHistorialScreen(navController = navController, historial = historial!!)
            } else {
                errorMessage?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
            }
        }
        composable("addHistorial") {
            AddHistorialScreen(
                historialRepository = historialRepository,
                onHistorialAdded = { navController.navigate("menu") }
            )
        }
    }
}