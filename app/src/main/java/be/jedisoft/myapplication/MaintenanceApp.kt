package be.jedisoft.myapplication

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import be.jedisoft.myapplication.ui.navigation.MaintenanceAppNavigation
import com.google.ar.core.Session


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaintenanceTopAppBar(
    @StringRes title: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = title)) },
        colors = TopAppBarDefaults.topAppBarColors(),
        modifier =modifier,
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.app_bar_back_button)
                    )
                }
            }
        }
    )
}


@Composable
fun MaintenanceApp(arEnabled: Boolean, arSession: Session, navController: NavHostController = rememberNavController()) {
    MaintenanceAppNavigation(navController = navController, arEnabled = arEnabled)
}