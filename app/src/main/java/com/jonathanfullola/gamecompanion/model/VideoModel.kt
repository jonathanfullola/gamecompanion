package com.jonathanfullola.gamecompanion.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoModel(
    val id: String? = null,
    @SerializedName("user_name") val username:String? = null,
    val title: String? = null
):Serializable

data class VideoResponse(
    val data: List<VideoModel>? = null
)