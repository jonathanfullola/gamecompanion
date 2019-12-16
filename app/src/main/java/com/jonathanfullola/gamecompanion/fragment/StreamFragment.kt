package com.jonathanfullola.gamecompanion.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.jonathanfullola.gamecompanion.R
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
        return inflater.inflate(R.layout.fragment_chat,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        lifecycleScope.launch(){
            val streamsResponse = TwitchApiService.endpoints.getStreams()
            val streams = streamsResponse.data ?: emptyList()
        }
        */

        TwitchApiService.endpoints.getStreams().enqueue(object :retrofit2.Callback<StreamsResponse>{
            override fun onFailure(call: retrofit2.Call<StreamsResponse>, t: Throwable) {
                Log.w("StreamFragment",t)
            }

            override fun onResponse(call: retrofit2.Call<StreamsResponse>, response: Response<StreamsResponse>) {

                Log.i("StreamFragment","++ onResponse ++")
                if(response.isSuccessful){
                    Log.i("StreamFragment", response.body()?.toString() ?: "Null body")
                }else{
                    Log.w("StreamFragment",response.message())
                }
            }
        })
    }
}
