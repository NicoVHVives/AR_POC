package be.jedisoft.myapplication

import android.util.Log
import android.view.MotionEvent
import com.google.ar.core.Frame
import dev.romainguy.kotlin.math.Float2
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

    override fun onFling(e1: MotionEvent?, e2: MotionEvent, node: Node?, velocity: Float2) {
        super.onFling(e1, e2, node, velocity)
        //Use this to detect swipe left or right in the tasknodes
        if(node != null) {
            if(node is TaskNode){
                var direction: Int = 0
                if((e1?.getX()?.minus(e2.getX()))!!.compareTo(-100.0f) < 0 || (e1?.getY()?.minus(e2.getY()))!!.compareTo(-100.0f) < 0){
                    direction = -1 //-1 = swipeNext (LTR)
                    Log.i("Maintenance", "On fling event LTR")
                }
                else if((e1?.getX()?.minus(e2.getX()))!!.compareTo(100.0f) > 0 || (e1?.getY()?.minus(e2.getY()))!!.compareTo(100.0f) > 0 ){
                    direction = 1 //1 = swipePrevious (RTL)
                    Log.i("Maintenance", "On fling event RTL")
                }
                val taskNode = node as TaskNode
                taskNode.onNodeFling(direction)
                Log.i("Maintenance", "On fling event executed on ${taskNode.tag} - Orientation: ${e2.orientation} - e1x: ${e1?.x.toString()} - e1y: ${e1?.y.toString()} - e2x: ${e2?.x.toString()} - e2y: ${e2?.y.toString()}")
            }

        }

    }



    }

