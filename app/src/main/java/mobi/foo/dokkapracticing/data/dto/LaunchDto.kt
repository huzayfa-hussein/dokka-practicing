package mobi.foo.dokkapracticing.data.dto

import com.google.gson.annotations.SerializedName
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel

/**
 * Data class representing launch information received from API.
 *
 * @property flightNumber The flight number of the launch.
 * @property dateLocal The local launch date in string format.
 * @property dateUnix The launch date in Unix timestamp format.
 * @property dateUtc The UTC launch date in string format.
 * @property isLaunchedSuccess Boolean indicating if the launch was successful.
 * @property launchYear The launch year in string format.
 * @property links Object containing links related to the launch.
 * @property missionName The name of the mission.
 * @property rocket Object containing information about the rocket used in the launch.
 */
data class LaunchDto(
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("launch_date_local")
    val dateLocal: String,
    @SerializedName("launch_date_unix")
    val dateUnix: Int,
    @SerializedName("launch_date_utc")
    val dateUtc: String,
    @SerializedName("launch_success")
    val isLaunchedSuccess: Boolean,
    @SerializedName("launch_year")
    val launchYear: String,
    val links: Links?,
    @SerializedName("mission_name")
    val missionName: String,
    val rocket: Rocket
) {
    /**
     * Converts LaunchDto object to LaunchModel object.
     *
     * @return The converted [LaunchModel] object.
     */
    fun toDomain(): LaunchModel {
        return LaunchModel(
            id = flightNumber.toLong(),
            image = links?.missionImage ?: "",
            missionName = missionName,
            date = dateLocal,
            rocketName = "${rocket.rocketName} / ${rocket.rocketType}",
            isLaunched = isLaunchedSuccess,
            days = 10,
            articleLink = links?.articleLink ?: ""
        )
    }

    companion object {

        /**
         * Converts a list of LaunchDto objects to a list of [LaunchModel] objects.
         *
         * @param data List of LaunchDto objects to be converted.
         * @return List of [LaunchModel] objects.
         */
        fun toDomainList(data: List<LaunchDto>?): List<LaunchModel> {
            val launches = arrayListOf<LaunchModel>()
            data?.forEach {
                launches.add(it.toDomain())
            }
            return launches
        }
    }
}