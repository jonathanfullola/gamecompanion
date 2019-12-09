package com.jonathanfullola.gamecompanion.network

import com.jonathanfullola.gamecompanion.model.StreamsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface TwitchApiService {

    @Headers("Client-ID: ywvglt0gib8rqdly0ejobehqfi071m")
    @GET("/streams")
    fun getStreams():retrofit2.Call<StreamsResponse>

    companion object{
        private var retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch..tv/helix")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var endpoints = retrofit.create<TwitchApiService>(TwitchApiService::class.java)
    }

}