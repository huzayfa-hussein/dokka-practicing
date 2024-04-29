package mobi.foo.dokkapracticing.domain.models.main

import java.io.Serializable

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
