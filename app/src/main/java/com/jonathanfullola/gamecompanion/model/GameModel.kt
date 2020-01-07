package com.jonathanfullola.gamecompanion.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GameModel(
    val id: String? = null,
    val name: String? = null,
    @SerializedName("box_art_url") val imageUrl:String? = null
):Serializable

data class GamesResponse(
    val data: List<GameModel>? = null
)