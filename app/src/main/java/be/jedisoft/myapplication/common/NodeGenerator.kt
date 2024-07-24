package be.jedisoft.myapplication.common

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.LifecycleCoroutineScope
import be.jedisoft.myapplication.MaintenanceNodeType
import be.jedisoft.myapplication.MenuItem
import be.jedisoft.myapplication.OverviewViewNode
import be.jedisoft.myapplication.R
import be.jedisoft.myapplication.TaskNode
import be.jedisoft.myapplication.ui.views.MaintenanceObjectTask
import com.google.android.filament.Engine
import com.google.ar.sceneform.rendering.ViewAttachmentManager
import io.github.sceneview.loaders.ModelLoader
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.math.Scale
import io.github.sceneview.node.Node
import kotlinx.coroutines.launch
import java.io.File


class NodeExecutors () {
    companion object{

        fun MenuButtonClicked(
            data: String,
            context: Context,
            menuItem: MenuItem,
            node: Node? = null,
            engine: Engine,
            modelLoader: ModelLoader,
            attachmentManager: ViewAttachmentManager,
            lifecycleScope: LifecycleCoroutineScope
        ){

                when(menuItem){
                    MenuItem.OpenManual -> ManualButtonClicked(data,context)
                    MenuItem.OpenTasks -> TaskButtonClicked(data,context,node,engine,modelLoader,attachmentManager,lifecycleScope)
                }

        }

        fun ManualButtonClicked(data: String, context: Context) {


                //context.grantUriPermission()
                Log.d("Maintenance", "Manual Button clicked")
                val storageLocation = context.getFilesDir().absolutePath + "/${data}.pdf"
                Log.d("Maintenance", storageLocation)
                val file = File(storageLocation)
                val prvUri = FileProvider.getUriForFile(
                    context,
                    context.packageName + ".fileprovider",
                    file
                )

                // Open file with user selected app
                val intent = Intent(Intent.ACTION_VIEW)

                intent.setDataAndType(prvUri, "application/pdf")
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                context.startActivity(Intent.createChooser(intent, "View PDF"))
            }

            fun TaskButtonClicked(
                data: String,
                context: Context,
                node: Node?,
                engine: Engine,
                modelLoader: ModelLoader,
                attachmentManager: ViewAttachmentManager,
                lifecycleScope: LifecycleCoroutineScope
            ) {

                lifecycleScope.launch {

                    var maintenanceNode = node as OverviewViewNode

                    val taskNode = TaskNode(engine, modelLoader, attachmentManager)
                    taskNode.maintenanceNodeType = MaintenanceNodeType.TaskNode
                    taskNode.rotation = Rotation(0.0f, 0.0f, 0.0f)
                    taskNode.scale = Scale(-1.0f, 1f, 1f)

                    taskNode.position = Position(0f, .6f, 0.0f)
                    taskNode.tag = data
                    taskNode.name = data
                    taskNode.onNodeTouched = {
                        Log.i("Maintenance", "Tasknode touched")
                        val taskObject =
                            taskNode.loadedView.findViewById<MaintenanceObjectTask>(R.id.maintenanceObjectTask)
                            taskObject.toggleTaskExecuted()

                    }
                    taskNode.onNodeFling = { direction ->

                            val taskObject =
                                taskNode.loadedView.findViewById<MaintenanceObjectTask>(R.id.maintenanceObjectTask)
                            if (direction == 1) {
                                taskObject.getNextMaintenanceTask()
                            }
                            else{
                                taskObject.getPreviousMaintenanceTask()
                            }

                    }
                    taskNode.loadView(
                        context,
                        R.layout.task_view,
                        onLoaded = { _, view ->
                            val titleNode =
                                view.findViewById<MaintenanceObjectTask>(R.id.maintenanceObjectTask)
                            titleNode.setMaintenanceObject(maintenanceNode.objectData)
                            taskNode.loadedView = view
                            maintenanceNode.addChildNode(taskNode)

                        })

                }

            }

        }





}







