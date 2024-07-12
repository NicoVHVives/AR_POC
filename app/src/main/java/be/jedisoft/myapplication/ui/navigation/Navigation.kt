package be.jedisoft.myapplication.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import be.jedisoft.myapplication.ui.mainscreen.MainScreen

@Composable
fun MaintenanceAppNavigation(
    navController: NavHostController,
    arEnabled: Boolean = false,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MaintenanceAppScreens.Mainscreen.name,
        modifier = modifier
    ) {
        composable(route = MaintenanceAppScreens.Mainscreen.name) {
            MainScreen(arEnabled = arEnabled)
        }
    }
}