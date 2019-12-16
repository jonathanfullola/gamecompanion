package com.jonathanfullola.gamecompanion.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jonathanfullola.gamecompanion.R
import com.jonathanfullola.gamecompanion.model.ChatMessage
import com.jonathanfullola.gamecompanion.util.COLLECTION_CHAT
import com.jonathanfullola.gamecompanion.util.COLLECTION_GUIDE
import kotlinx.android.synthetic.main.fragment_chat.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendButton.setOnClickListener {
            val text = messageEditText.text.toString()
            if(text.isNotBlank()){
                sendMessage(text)
            }
        }
    }

    private fun sendMessage(text: String){
        val chatMessage = ChatMessage(text = text, timesteamp = System.currentTimeMillis(), userId = FirebaseAuth.getInstance().currentUser?.uid)
        FirebaseFirestore.getInstance()
            .collection(COLLECTION_CHAT)
            .add(text)
            .addOnSuccessListener {

            }
            .addOnFailureListener{
                it.printStackTrace()
            }
    }
}
