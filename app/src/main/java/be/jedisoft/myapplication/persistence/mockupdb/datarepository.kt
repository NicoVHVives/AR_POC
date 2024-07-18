package be.jedisoft.myapplication.persistence.mockupdb

import be.jedisoft.myapplication.persistence.models.MaintenanceObject
import be.jedisoft.myapplication.persistence.models.MaintenanceParameter
import be.jedisoft.myapplication.persistence.models.MaintenanceType
import be.jedisoft.myapplication.persistence.models.ParameterType

class datarepository {
    companion object {

        var maintenanceObjects: HashMap<String, MaintenanceObject> = hashMapOf(
            "FL01-INV-M001" to MaintenanceObject(
                id = "FL01-INV-M001",
                type = MaintenanceType.Motor,
                documentUri = "FL01-INV-M001.pdf",
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
                        minValue = 30.0f,
                        maxValue = 45.0f
                    )
                )
            ),
            "FL01-OVN-M002" to MaintenanceObject(
                id = "FL01-OVN-M002",
                type = MaintenanceType.Valve,
                documentUri = "FL01-OVN-M002.pdf",
                parameters = arrayOf(
                    MaintenanceParameter(
                        name = "Aantal Schakelingen",
                        parameterType = ParameterType.Numeric,
                        value = "1839",
                        uom = ""
                    )
                )
            ),
            "FL01-UIT-M008" to MaintenanceObject(
                id = "FL01-UIT-M008",
                documentUri = "FL01-UIT-M008.pdf",
                type = MaintenanceType.Measurement,
                parameters = arrayOf(
                    MaintenanceParameter(
                        name = "Laatste Kalibratie",
                        parameterType = ParameterType.Text,
                        value = "2024/07/15 12:34:15",
                        uom = ""
                    ),
                    MaintenanceParameter(
                        name = "Meetwaarde",
                        parameterType = ParameterType.NumericMinMax,
                        value = "6.48",
                        uom = "pH",
                        minValue = 6.0f,
                        maxValue = 9.2f
                    )
                )
            ),
        )


        fun getMaintenanceObject(objectName: String): MaintenanceObject {
            return maintenanceObjects[objectName] ?: MaintenanceObject(
            id = "Unregistered Object",
            type = MaintenanceType.Measurement,
            documentUri = "",
            parameters = arrayOf()
            )

        }
    }

}




