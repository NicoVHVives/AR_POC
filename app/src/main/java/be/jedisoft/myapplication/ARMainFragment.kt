package be.jedisoft.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import be.jedisoft.myapplication.common.NodeExecutors
import be.jedisoft.myapplication.persistence.mockupdb.datarepository
import be.jedisoft.myapplication.ui.views.MaintenanceObjectView
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.sceneform.rendering.ViewAttachmentManager
import io.github.sceneview.ar.ARSceneView
import io.github.sceneview.ar.arcore.getUpdatedAugmentedImages
import io.github.sceneview.ar.node.AugmentedImageNode
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.math.Scale
import io.github.sceneview.node.Node
import kotlinx.coroutines.launch


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var sceneView: ARSceneView

    val augmentedImageNodes = mutableListOf<AugmentedImageNode>()


    fun generateButtonNode(context: Context, node:Node, data:String, layout:Int){

        lifecycleScope.launch {
            val attachmentManager =
                ViewAttachmentManager(context, sceneView)
            attachmentManager.onResume()
            val childNode = MaintenanceViewNode(sceneView.engine, sceneView.modelLoader, attachmentManager)
            //Rotate the node so that it come's readable above the QR-Code
            childNode.rotation = Rotation(0.0f,0.0f,0.0f)
            childNode.scale = Scale(-1.0f,1f, 1f)
            childNode.position = Position(-1f,0.5f,0.0f)
            childNode.tag = data
            childNode.maintenanceNodeType = MaintenanceNodeType.ButtonNode
            childNode.name = data + "|MANUAL"
            childNode.onNodeTouched = {
                NodeExecutors.ManualButtonClicked(data,context)
            }
            childNode.loadView(context,layout,  onLoaded = { _, view ->
                node.addChildNode(childNode)
            })
        }
    }

    fun generateMaintenanceNode(context: Context, node: Node, data: String){

        //Get the maintenance object
        val maintenanceObject = datarepository.getMaintenanceObject(data)

        lifecycleScope.launch {
            val attachmentManager =
                ViewAttachmentManager(context, sceneView)
            attachmentManager.onResume()
            val childNode = MaintenanceViewNode(sceneView.engine, sceneView.modelLoader, attachmentManager)
            //Rotate the node so that it come's readable above the QR-Code
            childNode.rotation = Rotation(0.0f,90.0f,0.0f)
            childNode.scale = Scale(-1.0f,1f, 1f)
            childNode.tag = data
            childNode.maintenanceNodeType = MaintenanceNodeType.Overview
            childNode.name = data + "|MAINTENANCE"
            childNode.onNodeTouched = {
                generateButtonNode(context,childNode,data,R.layout.maintenance_manual)
            }
            childNode.loadView(context, R.layout.card_view,  onLoaded = { _, view ->
                val titleNode = view.findViewById<MaintenanceObjectView>(R.id.maintenanceObjectView)
                titleNode.setMaintenanceObject(maintenanceObject)
                node.addChildNode(childNode)
            })
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sceneView = view.findViewById<ARSceneView>(R.id.sceneView).apply {
            configureSession { session, config ->
                var db: AugmentedImageDatabase

                requireContext().assets.open("arImgDatabase.imgdb").apply {
                    db = AugmentedImageDatabase.deserialize(session,this)
                }
                config.augmentedImageDatabase = db

            }
            onSessionUpdated = { _, frame ->
                frame.getUpdatedAugmentedImages().forEach { augmentedImage ->
                    if (augmentedImageNodes.none { it.imageName == augmentedImage.name }) {
                        val augmentedImageNode = AugmentedImageNode(engine, augmentedImage).apply {
                            Log.i("Augmented Image",augmentedImage.name)
                            when (augmentedImage.name) {
                                "FL01-OVN-M002.png" -> {

                                    generateMaintenanceNode(requireContext(), this,"FL01-OVN-M002")
                                    //generateManualNode(requireContext(), this,"FL01-OVN-M002")
                                }
                                "FL01-INV-M001.png" -> {
                                    generateMaintenanceNode(requireContext(), this,"FL01-INV-M001")
                                    //generateManualNode(requireContext(), this,"FL01-INV-M001")
                                }
                                "FL01-UIT-M008.png" -> {
                                    generateMaintenanceNode(requireContext(), this,"FL01-UIT-M008")
                                    //generateManualNode(requireContext(), this,"FL01-UIT-M008")
                                }
                                else -> {
                                    //Do Nothing
                                }
                            }

                        }

                        addChildNode(augmentedImageNode)
                        augmentedImageNodes += augmentedImageNode
                    }
                }
            }
            //onTouchEvent = { event, hitresult ->
            //    Log.i("MaintenanceObjectView","ViewNode was touched")
            //    val node : ViewNode = hitresult?.node as ViewNode
            //    val renderable = node.renderable
            //    val view = renderable?.view
            //    true

            //}
            onGestureListener = MaintenanceGestureListener()



        }
    }
}