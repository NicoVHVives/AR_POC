package be.jedisoft.myapplication

import android.view.View
import be.jedisoft.myapplication.persistence.models.MaintenanceObject
import com.google.android.filament.Engine
import com.google.ar.sceneform.rendering.ViewAttachmentManager
import io.github.sceneview.loaders.ModelLoader
import io.github.sceneview.node.ViewNode


enum class MaintenanceNodeType(val value: Int) {
    Undefined(0),
    Overview(1),
    ButtonNode(2),
    TaskNode(3)

}

enum class MenuItem(val value: Int) {
    OpenManual(1),
    OpenTasks(2)
}

open class MaintenanceViewNode(engine: Engine,
                          modelLoader: ModelLoader,
                          viewAttachmentManager: ViewAttachmentManager,) : ViewNode(engine,modelLoader,viewAttachmentManager){

                              var tag: String = ""
                              var maintenanceNodeType: MaintenanceNodeType = MaintenanceNodeType.Undefined
                              var onNodeTouched: () -> Unit = {}

}

class TaskNode(engine: Engine,
               modelLoader: ModelLoader,
               viewAttachmentManager: ViewAttachmentManager) : MaintenanceViewNode(engine,modelLoader,viewAttachmentManager){
                   var onNodeFling: (direction: Int) -> Unit = {}
                   lateinit var loadedView: View
               }

class OverviewViewNode(engine: Engine,
                       modelLoader: ModelLoader,
                       viewAttachmentManager: ViewAttachmentManager,val objectData: MaintenanceObject) : MaintenanceViewNode(engine,modelLoader,viewAttachmentManager){



                       }

class MenuItemViewNode(engine: Engine,
                       modelLoader: ModelLoader,
                       viewAttachmentManager: ViewAttachmentManager, val menuItem: MenuItem) : MaintenanceViewNode(engine,modelLoader,viewAttachmentManager){

                       }