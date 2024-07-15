package be.jedisoft.myapplication.persistence.models


enum class MaintenanceType(val value: Int) {
    Motor(1),
    Valve(2),
    Measurement( 3)

}

enum class ParameterType(val value: Int) {
    Numeric(1),
    NumericMinMax( 2),
    Text(3)


}

data class MaintenanceParameter(
    val name: String,
    val value: String,
    val parameterType: ParameterType,
    val uom: String,
    val minValue: Float = 0f,
    val maxValue: Float = 0f
)

data class MaintenanceObject (

    val id: String,
    val type: MaintenanceType,
    val parameters: Array<MaintenanceParameter>

)