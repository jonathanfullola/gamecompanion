package com.jonathanfullola.gamecompanion.model

import com.google.gson.annotations.SerializedName

data class StreamModel(
    val id: String? = null,
    @SerializedName("user_id") val userId: String? = null,
    @SerializedName("user_id") val username: String? = null,
    val title: String? = null,
    @SerializedName ("viewer_count") val viewerCount: Int? = null,
    @SerializedName ("thumbnail_url") val thumbnailUrl: String? = null
)

data class StreamsResponse(

)