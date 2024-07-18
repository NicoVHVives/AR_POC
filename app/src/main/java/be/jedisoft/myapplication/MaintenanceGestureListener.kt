package be.jedisoft.myapplication

import android.util.Log
import android.view.MotionEvent
import com.google.ar.core.Frame
import io.github.sceneview.ar.node.AugmentedImageNode
import io.github.sceneview.node.Node

class MaintenanceGestureListener() :
    io.github.sceneview.gesture.GestureDetector.SimpleOnGestureListener()
{



    override fun onSingleTapConfirmed(e: MotionEvent, node: Node?) {
        super.onSingleTapConfirmed(e, node)
        if(node != null) {
            val maintNode = node as MaintenanceViewNode
            maintNode.onNodeTouched()
            Log.i("Maintenance", "Single tap event executed on ${maintNode.tag}")
        }
    }

    override fun onDoubleTap(e: MotionEvent, node: Node?) {
        super.onDoubleTap(e, node)



        if (node != null) {
            val maintNode = node as MaintenanceViewNode
            Log.i("Maintenance", "Double tap event executed on ${maintNode.tag}")

            maintNode.childNodes.forEach { n -> n.destroy() }

        }
    }

    }

