import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.Screen.AddHistorialScreen
import com.example.historialmedico.Screen.EditHistorialScreen
import com.example.historialmedico.Screen.LoginScreen
import com.example.historialmedico.Screen.MenuScreen
import com.example.historialmedico.Screen.ViewHistorialScreen

@Composable
fun AppNavigation() {

    val firebaseHistorialRepository = FirebaseHistorialRepository()
    var currentScreen by remember { mutableStateOf(Screen.Login) }
    var selectedHistorialId by remember { mutableStateOf<String?>(null) }

    when (currentScreen) {
        Screen.Login -> {
            LoginScreen(onLoginSuccess = {
                currentScreen = Screen.Menu
            })
        }
        Screen.Menu -> {
            MenuScreen(
                historialRepository = firebaseHistorialRepository,
                onAddHistorialClick = { currentScreen = Screen.AddHistorial },
                onViewHistorialClick = { currentScreen = Screen.ViewHistorial }
            )
        }
        Screen.AddHistorial -> {
            AddHistorialScreen(
                historialRepository = firebaseHistorialRepository,
                onHistorialAdded = { currentScreen = Screen.Menu },
                onBacktoMenu = { currentScreen = Screen.Menu }
            )
        }
        Screen.ViewHistorial -> {
            ViewHistorialScreen(
                historialRepository = firebaseHistorialRepository,
                onBack = { currentScreen = Screen.Menu },
                onEditHistorialClick = { historial ->
                    selectedHistorialId = historial.id ?: ""
                    currentScreen = Screen.EditHistorial
                }
            )
        }
        Screen.EditHistorial -> {
            selectedHistorialId?.let { historialId ->
                EditHistorialScreen(
                    historialId = historialId,
                    onBackToView = { currentScreen = Screen.ViewHistorial }
                )
            }
        }
    }
}

enum class Screen {
    Login,
    Menu,
    AddHistorial,
    ViewHistorial,
    EditHistorial
}