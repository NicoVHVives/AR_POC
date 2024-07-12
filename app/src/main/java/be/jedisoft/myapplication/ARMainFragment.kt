package be.jedisoft.myapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.ar.core.AugmentedImageDatabase
import io.github.sceneview.ar.ARSceneView
import io.github.sceneview.ar.arcore.getUpdatedAugmentedImages
import io.github.sceneview.ar.node.AugmentedImageNode
import io.github.sceneview.math.Position
import io.github.sceneview.node.ImageNode
import io.github.sceneview.node.ModelNode


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var sceneView: ARSceneView

    val augmentedImageNodes = mutableListOf<AugmentedImageNode>()

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
            onSessionUpdated = { session, frame ->
                frame.getUpdatedAugmentedImages().forEach { augmentedImage ->
                    if (augmentedImageNodes.none { it.imageName == augmentedImage.name }) {
                        val augmentedImageNode = AugmentedImageNode(engine, augmentedImage).apply {
                            Log.i("Augmented Image",augmentedImage.name)
                            when (augmentedImage.name) {
                                "FL01-OVN-M002.png" -> {
                                    addChildNode(
                                        ModelNode(
                                            modelInstance = modelLoader.createModelInstance(
                                                assetFileLocation = "models/FL01-OVN-M002.glb"
                                            ),
                                            scaleToUnits = 0.5f,
                                            centerOrigin = Position(x= 0.0f, y=0.0f, z=0.5f ),
                                        )
//                                        ImageNode(
//                                            materialLoader = materialLoader,
//                                            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.fl01_ovn_m002_t)
//                                        )
                                    )
                                }
                                "FL01-INV-M001.png" -> {
                                    addChildNode(
                                        ModelNode(
                                            modelInstance = modelLoader.createModelInstance(
                                                assetFileLocation = "models/FL01-INV-M001.glb"
                                            ),
                                            scaleToUnits = 0.1f,
                                            centerOrigin = Position(0.0f)
                                        )
                                    )
                                }
                                "FL01-UIT-M008.png" -> {
                                    addChildNode(
                                        ModelNode(
                                            modelInstance = modelLoader.createModelInstance(
                                                assetFileLocation = "models/FL01-UIT-M008.glb"
                                            ),
                                            scaleToUnits = 0.1f,
                                            centerOrigin = Position(0.0f)
                                        )
//                                        ImageNode(
//                                            materialLoader = materialLoader,
//                                            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.fl01_uit_m008_t)
//                                        )
                                    )
                                }
                                else -> {
                                    addChildNode(
                                        ModelNode(
                                            modelInstance = modelLoader.createModelInstance(
                                                assetFileLocation = "models/FL01-UIT-M008.glb"
                                            ),

                                            scaleToUnits = 0.1f,
                                            centerOrigin = Position(0.0f)
                                        )
//                                        ImageNode(
//                                            materialLoader = materialLoader,
//                                            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.fl01_inv_m001_t)
//                                        )
                                    )
                                }
                            }
                        }
                        addChildNode(augmentedImageNode)
                        augmentedImageNodes += augmentedImageNode
                    }
                }
            }
            onTouchEvent = {
                    e, hitresult ->
                Log.i("Touched",hitresult?.node.toString())
                true
            }
        }
    }
}