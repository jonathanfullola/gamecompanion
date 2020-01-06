package com.jonathanfullola.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.jonathanfullola.gamecompanion.R
import com.jonathanfullola.gamecompanion.adapter.StreamAdapter
import com.jonathanfullola.gamecompanion.model.GamesResponse
import com.jonathanfullola.gamecompanion.model.StreamsResponse
import com.jonathanfullola.gamecompanion.network.TwitchApiService
import kotlinx.android.synthetic.main.fragment_streams.*
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

        val adapter = StreamAdapter(ArrayList())
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter


        //Get Streams
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

                    //Get list of all Game IDs
                    val gameIds = streams?.map{it.gameId ?:""}

                     //Get Games
                    gameIds.let{ids->
                        TwitchApiService.endpoints.getGames(ids).enqueue(object :retrofit2.Callback<GamesResponse>{
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
                                    adapter.list = ArrayList(streams.map {it.game?.name ?: ""})
                                    adapter.notifyDataSetChanged()
                                    Log.i("StreamsFragment", "Got games $games")
                                    Log.i("StreamsFragment", "Got streams with games $streams")

                                }
                                else{
                                    Log.w("StreamsFragment", "Error getting games")
                                }
                            }
                        } )
                    }

                }else{
                    Log.w("StreamFragment",response.message())
                }
            }
        })
    }
}
