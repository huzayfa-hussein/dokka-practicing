package mobi.foo.dokkapracticing.domain.models.main

import java.io.Serializable

/**
 * LaunchModel represents a data class that holds information about a launch.
 * It implements Serializable to enable serialization of the object.
 * @property id The unique identifier of the launch.
 * @property image The URL of the image associated with the launch.
 * @property missionName The name of the mission.
 * @property date The date of the launch.
 * @property rocketName The name of the rocket used for the launch.
 * @property days The number of days since the launch.
 * @property isLaunched A boolean indicating whether the launch has been completed.
 * @property articleLink The URL of the article related to the launch.
 */
data class LaunchModel(

    var id: Long = 0L,
    var image: String,
    var missionName: String,
    var date: String,
    var rocketName: String,
    var days: Int,
    var isLaunched: Boolean,
    var articleLink: String
) : Serializable
