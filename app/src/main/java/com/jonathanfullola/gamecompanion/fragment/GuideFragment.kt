package com.jonathanfullola.gamecompanion.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.jonathanfullola.gamecompanion.R
import com.jonathanfullola.gamecompanion.model.GuideModel
import com.jonathanfullola.gamecompanion.util.COLLECTION_GUIDE
import com.jonathanfullola.gamecompanion.adapter.GuideListAdapter
import kotlinx.android.synthetic.main.fragment_guide.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class GuideFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        val secretAdapter = GuideListAdapter()


        FirebaseFirestore.getInstance().collection(COLLECTION_GUIDE).get()
            .addOnSuccessListener {
                val guideList = it.toObjects(GuideModel::class.java)
                secretAdapter.elements = ArrayList(guideList.toList())
                recyclerView.adapter = secretAdapter

            }
            .addOnFailureListener{
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            }

    }

}
