package com.jonathanfullola.gamecompanion.network

import com.jonathanfullola.gamecompanion.model.GameModel
import com.jonathanfullola.gamecompanion.model.GamesResponse
import com.jonathanfullola.gamecompanion.model.StreamsResponse
import com.jonathanfullola.gamecompanion.model.VideoResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TwitchApiService {

    //Endpoint Streams
    @Headers("Client-ID: $clientId")
    @GET("streams?game_id=11557")
    fun getStreams(): retrofit2.Call<StreamsResponse>

    //Endpoint Games
    @Headers("Client-ID: $clientId")
    @GET("games")
    fun getGames(@Query("id") gameIds: List<String>?):retrofit2.Call<GamesResponse>

    //Endpoint Videos
    @Headers("Client-ID: $clientId")
    @GET("videos")
    fun getVideos(): retrofit2.Call<VideoResponse>

    //TO DO: ASK FOR ENDPOINTS, MAYBE ADD ENDPOINT GAMES
    companion object{

        private const val clientId = "ywvglt0gib8rqdly0ejobehqfi071m"

        //Http client
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/helix/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Endpoints
        var endpoints = retrofit.create<TwitchApiService>(TwitchApiService::class.java)
    }

}