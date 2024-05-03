package mobi.foo.dokkapracticing.data.dto

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("article_link")
    val articleLink: String?,
    @SerializedName("mission_patch_small")
    val missionImage: String?
)