package be.jedisoft.myapplication.persistence.mockupdb

import be.jedisoft.myapplication.persistence.models.MaintenanceObject
import be.jedisoft.myapplication.persistence.models.MaintenanceParameter
import be.jedisoft.myapplication.persistence.models.MaintenanceType
import be.jedisoft.myapplication.persistence.models.ParameterType

class datarepository {
    companion object {
        fun getMaintenanceObject(objectName : String) : MaintenanceObject{
            return MaintenanceObject(
                id = "FL01-INV-M001",
                type = MaintenanceType.Motor,
                parameters = arrayOf(
                    MaintenanceParameter(
                        name = "Temperatuur",
                        parameterType = ParameterType.NumericMinMax,
                        value = "75.00",
                        uom = "°C",
                        minValue = 70.0f,
                        maxValue = 90.0f
                    ),
                    MaintenanceParameter(
                        name = "Toerental",
                        parameterType = ParameterType.Numeric,
                        value = "1499",
                        uom = "rpm"
                    ),
                    MaintenanceParameter(
                        name = "Stroom",
                        parameterType = ParameterType.NumericMinMax,
                        value = "29.47",
                        uom = "A",
                        minValue =  30.0f,
                        maxValue = 45.0f
                    )
                )
            )
        }
    }




}