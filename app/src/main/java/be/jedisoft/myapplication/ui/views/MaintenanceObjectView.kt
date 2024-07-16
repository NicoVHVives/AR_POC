package be.jedisoft.myapplication.ui.views


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.jedisoft.myapplication.R
import be.jedisoft.myapplication.persistence.models.MaintenanceObject
import be.jedisoft.myapplication.ui.adapters.MaintenanceParameterAdapter

class MaintenanceObjectView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : LinearLayout(context, attrs, defStyleAttr) {

        private var objectNameTextView: TextView
        private var typeNameTextView: TextView
        private var parameterRecyclerView: RecyclerView
        private lateinit var maintenanceObject: MaintenanceObject

        init {
            orientation = VERTICAL
            LayoutInflater.from(context).inflate(R.layout.maintenance_object_view, this, true)
            objectNameTextView = findViewById(R.id.maint_obj_name)
            typeNameTextView = findViewById(R.id.maint_type_name)
            parameterRecyclerView = findViewById(R.id.maint_param_view)
        }

        fun setMaintenanceObject(obj: MaintenanceObject) {
            maintenanceObject = obj
            objectNameTextView.text = obj.id
            typeNameTextView.text = obj.type.name
            parameterRecyclerView.layoutManager = LinearLayoutManager(context)
            parameterRecyclerView.adapter = MaintenanceParameterAdapter(obj.parameters.toList())
        }


    }