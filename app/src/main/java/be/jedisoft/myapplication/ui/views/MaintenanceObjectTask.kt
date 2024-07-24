package be.jedisoft.myapplication.ui.views


import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import be.jedisoft.myapplication.R
import be.jedisoft.myapplication.persistence.models.MaintenanceObject

class MaintenanceObjectTask @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : LinearLayout(context, attrs, defStyleAttr) {

        private var taskDetailTextView: TextView
        private var taskCurrentnrTextView: TextView
        private var taskAllNrTextView: TextView
        private var layout: LinearLayout
        private var sequenceNo: Int
        private lateinit var maintenanceObject: MaintenanceObject

        init {
            orientation = VERTICAL
            sequenceNo = 1
            LayoutInflater.from(context).inflate(R.layout.maintenance_object_task, this, true)
            taskDetailTextView = findViewById(R.id.maint_task_explanation)
            taskCurrentnrTextView = findViewById(R.id.maint_task_current)
            taskAllNrTextView = findViewById(R.id.maint_task_all)
            layout = findViewById(R.id.maint_task_layout)

        }

        fun setMaintenanceObject(obj: MaintenanceObject) {
            maintenanceObject = obj
            if(obj.tasks.size > 0){
                setTextFields(sequenceNo)
                taskAllNrTextView.text = obj.tasks.size.toString()

            }

        }

        fun getNextMaintenanceTask(){
            if(sequenceNo < maintenanceObject.tasks.size){
                sequenceNo++
            }
            else{
                sequenceNo = 1
            }

            setTextFields(sequenceNo)
        }

        fun getPreviousMaintenanceTask(){
            if(sequenceNo > 1){
                sequenceNo--
            }
            else{
                sequenceNo = maintenanceObject.tasks.size
            }

            setTextFields(sequenceNo)
        }

    fun toggleTaskExecuted(){
        if(maintenanceObject.tasks.elementAt(sequenceNo-1).taskFinished) {
            maintenanceObject.tasks.elementAt(sequenceNo - 1).taskFinished = false
            layout.background = ContextCompat.getDrawable(this.context,R.drawable.bg_black_transparent_rounded)
        }
        else {
            maintenanceObject.tasks.elementAt(sequenceNo - 1).taskFinished = true
            layout.setBackgroundColor(Color.rgb(0, 102, 0))
        }

    }

        private fun setTextFields(currentSequenceNo: Int){
            taskDetailTextView.text = maintenanceObject.tasks.elementAt(currentSequenceNo-1).taskExplanation
            taskCurrentnrTextView.text = currentSequenceNo.toString()
            if(maintenanceObject.tasks.elementAt(sequenceNo-1).taskFinished){
                layout.setBackgroundColor(Color.rgb(0,102,0))

            }
            else {
                layout.background = ContextCompat.getDrawable(this.context,R.drawable.bg_black_transparent_rounded)
            }

        }


    }