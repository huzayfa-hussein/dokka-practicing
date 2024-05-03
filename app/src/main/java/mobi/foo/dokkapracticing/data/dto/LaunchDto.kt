package mobi.foo.dokkapracticing.data.dto

import com.google.gson.annotations.SerializedName
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel

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
        fun toDomainList(data: List<LaunchDto>?): List<LaunchModel> {
            val launches = arrayListOf<LaunchModel>()
            data?.forEach {
                launches.add(it.toDomain())
            }
            return launches
        }
    }
}