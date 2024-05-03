package mobi.foo.dokkapracticing.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a rocket.
 * @property rocketId The unique identifier of the rocket.
 * @property rocketName The name of the rocket.
 * @property rocketType The type of the rocket.
 */
data class Rocket(
    @SerializedName("rocket_id")
    val rocketId: String,
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String
)