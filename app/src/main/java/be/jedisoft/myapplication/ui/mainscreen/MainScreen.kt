package be.jedisoft.myapplication.ui.mainscreen


import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import be.jedisoft.myapplication.MaintenanceTopAppBar
import be.jedisoft.myapplication.R
import android.graphics.Camera
import android.opengl.GLSurfaceView
import android.view.LayoutInflater
import android.view.ViewGroup

private const val TAG = "MainScreen"

class MainScreen : ComponentActivity() {



    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

}



@Composable
fun MainScreen(
    arEnabled: Boolean = false,
    modifier: Modifier = Modifier
)
{
    val context = LocalContext.current
    var camera: Camera? by remember {mutableStateOf(null)}

    Scaffold(
        topBar = {
            MaintenanceTopAppBar(
                title = R.string.nav_mainscreen,
                canNavigateBack = false,
                navigateUp = { /*Not Implemented*/ })
        }
    ) {
        AndroidView(
            modifier = modifier.padding(it),
            factory = { context ->
                val view = LayoutInflater.from(context).inflate(R.layout.activity_main,null,false);
                //val glRenderer = view.findViewById<GLSurfaceView>(R.id.surfaceview).apply {
                view
                })
            }



    }