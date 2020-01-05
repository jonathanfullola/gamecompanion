package com.jonathanfullola.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.jonathanfullola.gamecompanion.R
import com.jonathanfullola.gamecompanion.model.GamesResponse
import com.jonathanfullola.gamecompanion.model.StreamsResponse
import com.jonathanfullola.gamecompanion.network.TwitchApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class StreamsFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        return inflater.inflate(R.layout.fragment_streams,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TwitchApiService.endpoints.getStreams().enqueue(object :retrofit2.Callback<StreamsResponse>{
            override fun onFailure(call: retrofit2.Call<StreamsResponse>, t: Throwable) {
                Log.w("StreamFragment",t)
            }

            override fun onResponse(call: retrofit2.Call<StreamsResponse>, response: Response<StreamsResponse>) {

                Log.i("StreamFragment","++ onResponse ++")
                if(response.isSuccessful){
                    Log.i("StreamFragment", response.body()?.toString() ?: "Null body")
                    //response.body()?.data?.first()?.getThumbnailUrl()
                    val streams = response.body()?.data

                    //Iterate Streams
                    streams?.forEach{
                        //Get Games
                        it.gameId.let{gameId->
                            TwitchApiService.endpoints.getGames(gameId).enqueue(object :retrofit2.Callback<GamesResponse>{
                                override fun onFailure(
                                    call: retrofit2.Call<GamesResponse>,
                                    t: Throwable) {
                                    Log.w("StreamFragment",t)
                                }

                                override fun onResponse(
                                    call: retrofit2.Call<GamesResponse>,
                                    response: retrofit2.Response<GamesResponse>
                                ) {
                                    if(response.isSuccessful){
                                        val games = response.body()?.data
                                        streams?.forEach{stream->
                                            games?.forEach{game->
                                                if(stream.gameId == game.id){
                                                    stream.game = game
                                                }
                                            }
                                        }
                                        Log.i("StreamsFragment", "Got games $games")
                                        Log.i("StreamsFragment", "Got streams with games $streams")

                                    }
                                    else{
                                        Log.w("StreamsFragment", "Error getting games")
                                    }
                                }
                            } )
                        }
                    }
                }else{
                    Log.w("StreamFragment",response.message())
                }
            }
        })
    }
}
