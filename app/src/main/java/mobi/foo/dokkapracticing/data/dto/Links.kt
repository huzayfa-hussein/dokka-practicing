package mobi.foo.dokkapracticing.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Represents links related to a space mission.
 * @property articleLink The link to the article related to the mission.
 * @property missionImage The link to the small mission patch image.
 */
data class Links(
    @SerializedName("article_link")
    val articleLink: String?,
    @SerializedName("mission_patch_small")
    val missionImage: String?
)