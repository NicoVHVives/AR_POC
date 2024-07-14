package be.jedisoft.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.sceneform.rendering.ViewAttachmentManager
import io.github.sceneview.ar.ARSceneView
import io.github.sceneview.ar.arcore.getUpdatedAugmentedImages
import io.github.sceneview.ar.node.AugmentedImageNode
import io.github.sceneview.math.Rotation
import io.github.sceneview.math.Scale
import io.github.sceneview.node.Node
import io.github.sceneview.node.ViewNode
import kotlinx.coroutines.launch


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var sceneView: ARSceneView

    val augmentedImageNodes = mutableListOf<AugmentedImageNode>()

    fun generateViewNode(context: Context, node: Node, data: String){
        lifecycleScope.launch {
            val attachmentManager =
                ViewAttachmentManager(context, sceneView)
            attachmentManager.onResume()
            val childNode = ViewNode(sceneView.engine, sceneView.modelLoader, attachmentManager)
            //Rotate the node so that it come's readable above the QR-Code
            childNode.rotation = Rotation(0.0f,90.0f,0.0f)
            childNode.scale = Scale(-1.0f,1f, 1f)
            childNode.loadView(context, R.layout.card_view,  onLoaded = { _, view ->
                var titleNode = view.findViewById<TextView>(R.id.node_title)
                titleNode.setText(data)
                node.addChildNode(childNode)
            })
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sceneView = view.findViewById<ARSceneView>(R.id.sceneView).apply {
            configureSession { session, config ->
                var db: AugmentedImageDatabase;

                requireContext().assets.open("arImgDatabase.imgdb").apply {
                    db = AugmentedImageDatabase.deserialize(session,this)
                }
                config.augmentedImageDatabase = db;

            }
            onSessionUpdated = { _, frame ->
                frame.getUpdatedAugmentedImages().forEach { augmentedImage ->
                    if (augmentedImageNodes.none { it.imageName == augmentedImage.name }) {
                        val augmentedImageNode = AugmentedImageNode(engine, augmentedImage).apply {
                            Log.i("Augmented Image",augmentedImage.name)
                            when (augmentedImage.name) {
                                "FL01-OVN-M002.png" -> {
                                    generateViewNode(requireContext(), this,"FL01-OVN-M002")
                                }
                                "FL01-INV-M001.png" -> {
                                    generateViewNode(requireContext(), this,"FL01-INV-M001")
                                }
                                "FL01-UIT-M008.png" -> {
                                    generateViewNode(requireContext(), this,"FL01-UIT-M008")
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
        }
    }
}