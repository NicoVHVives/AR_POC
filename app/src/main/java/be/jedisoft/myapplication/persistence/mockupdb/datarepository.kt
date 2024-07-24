package be.jedisoft.myapplication.persistence.mockupdb

import be.jedisoft.myapplication.persistence.models.MaintenanceObject
import be.jedisoft.myapplication.persistence.models.MaintenanceParameter
import be.jedisoft.myapplication.persistence.models.MaintenanceTask
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
                ),
                tasks = arrayOf(
                    MaintenanceTask(
                        sequenceNo = 1,
                        taskExplanation = "Draai de 4 schroeven van \n de verdeeldoos open",
                        taskFinished = false
                    ),
                    MaintenanceTask(
                        sequenceNo = 2,
                        taskExplanation = "Visuele controle \n verdeeldoos",
                        taskFinished = false
                    ),
                    MaintenanceTask(
                        sequenceNo = 3,
                        taskExplanation = "Controleer de verbindingen \n met de momentsleutel",
                        taskFinished = false
                    ),
                    MaintenanceTask(
                        sequenceNo = 4,
                        taskExplanation = "Draai de 4 schroeven van \n de verdeeldoos terug vast",
                        taskFinished = false
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
                ),
                tasks = arrayOf(
                    MaintenanceTask(
                        sequenceNo = 1,
                        taskExplanation = "Controleer werking van de klep \n door manueel te schakelen",
                        taskFinished = false
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
                ),
                tasks = arrayOf(
                    MaintenanceTask(
                        sequenceNo = 1,
                        taskExplanation = "Dompel de sonde onder in PH4 \n schrijf de uitgelezen waarde \n neer",
                        taskFinished = false
                    ),
                    MaintenanceTask(
                        sequenceNo = 2,
                        taskExplanation = "Dompel de sonde onder in PH7 \n schrijf de uitgelezen waarde \n neer",
                        taskFinished = false
                    ),
                    MaintenanceTask(
                        sequenceNo = 3,
                        taskExplanation = "Kalibreer het toestel \n aan de hand van de uitgelezen waarden",
                        taskFinished = false
                    ),
                    MaintenanceTask(
                        sequenceNo = 4,
                        taskExplanation = "Controleer door de sonde \n terug onder te dompelen in \n PH4 en PH7",
                        taskFinished = false
                    )

                )
            ),
        )


        fun getMaintenanceObject(objectName: String): MaintenanceObject {
            return maintenanceObjects[objectName] ?: MaintenanceObject(
            id = "Unregistered Object",
            type = MaintenanceType.Measurement,
            documentUri = "",
            parameters = arrayOf()            ,
            tasks = arrayOf()
            )

        }
    }

}




