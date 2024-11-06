import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.Screen.AddHistorialScreen
import com.example.historialmedico.Screen.EditHistorialScreen
import com.example.historialmedico.Screen.LoginScreen
import com.example.historialmedico.Screen.MenuScreen
import com.example.historialmedico.Screen.ViewHistorialScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = { navController.navigate("menu") })
        }
        composable("menu") {
            MenuScreen(
                historialRepository = FirebaseHistorialRepository(),
                onAddHistorialClick = { navController.navigate("addHistorial") },
                onViewHistorialClick = { navController.navigate("viewHistorial") }
            )
        }
        composable("addHistorial") {
            AddHistorialScreen(
                historialRepository = FirebaseHistorialRepository(),
                onHistorialAdded = { navController.popBackStack() }
            )
        }
        composable("viewHistorial") {
            ViewHistorialScreen(
                historialRepository = FirebaseHistorialRepository(),
                onBack = { navController.popBackStack() },
                onEditHistorialClick = { historial ->
                    navController.navigate("editHistorial/${historial.id}")
                }
            )
        }
        composable("editHistorial/{historialId}") { backStackEntry ->
            val historialId = backStackEntry.arguments?.getString("historialId")
            if (historialId != null) {
                EditHistorialScreen(navController = navController, historialId = historialId)
            }
        }
    }
}