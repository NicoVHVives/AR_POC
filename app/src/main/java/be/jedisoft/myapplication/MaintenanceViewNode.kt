package be.jedisoft.myapplication

import com.google.android.filament.Engine
import com.google.ar.sceneform.rendering.ViewAttachmentManager
import io.github.sceneview.loaders.ModelLoader
import io.github.sceneview.node.ViewNode


enum class MaintenanceNodeType(val value: Int) {
    Undefined(0),
    Overview(1),
    ButtonNode(2)

}

class MaintenanceViewNode(engine: Engine,
                          modelLoader: ModelLoader,
                          viewAttachmentManager: ViewAttachmentManager,) : ViewNode(engine,modelLoader,viewAttachmentManager){

                              var tag: String = ""
                              var maintenanceNodeType: MaintenanceNodeType = MaintenanceNodeType.Undefined
                              var onNodeTouched: () -> Unit = {}

}