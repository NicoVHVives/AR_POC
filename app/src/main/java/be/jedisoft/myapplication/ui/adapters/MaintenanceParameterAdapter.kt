package be.jedisoft.myapplication.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.jedisoft.myapplication.R
import be.jedisoft.myapplication.persistence.models.MaintenanceParameter
import be.jedisoft.myapplication.persistence.models.ParameterType

class MaintenanceParameterAdapter(private val parameterList: List<MaintenanceParameter>):
    RecyclerView.Adapter<MaintenanceParameterAdapter.ViewHolder>() {

    class ViewHolder(parameterView: View) : RecyclerView.ViewHolder(parameterView) {
        val parameterNameText: TextView = parameterView.findViewById(R.id.maint_parameter_name)
        val parameterValueText: TextView = parameterView.findViewById(R.id.maint_parameter_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.maintenance_object_parameter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return parameterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parameter = parameterList[position]
        holder.parameterNameText.text = parameter.name
        holder.parameterValueText.text = parameter.value


        if(parameter.parameterType == ParameterType.NumericMinMax){
            val parameterVal = parameter.value.toFloat()

            if(parameterVal < parameter.minValue || parameterVal > parameter.maxValue){
                holder.parameterNameText.setTextColor(Color.RED)
                holder.parameterValueText.setTextColor(Color.RED)
            }
            else {
                holder.parameterNameText.setTextColor(Color.GREEN)
                holder.parameterValueText.setTextColor(Color.GREEN)
            }

        }



    }


}