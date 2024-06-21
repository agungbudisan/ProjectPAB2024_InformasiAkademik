package com.pab2024klsa.informasiakademik.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pab2024klsa.informasiakademik.R
import com.pab2024klsa.informasiakademik.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
//    private lateinit var rvProfile: RecyclerView
//    private val list = ArrayList<Profile>()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        rvProfile = binding.rvHomes
//        rvProfile.setHasFixedSize(true)
//
//        list.addAll(getListProfile())
//        showRecyclerList()

        return view
    }

//    private fun getListProfile(): ArrayList<Profile> {
//        val dataImg = resources.obtainTypedArray(R.array.data_img)
//        val dataName = resources.getStringArray(R.array.data_name)
//        val dataNim = resources.getStringArray(R.array.data_nim)
//
//        val listProfile = ArrayList<Profile>()
//        for (i in dataName.indices) {
//            val profile = Profile(dataImg.getResourceId(i, -1), dataName[i], dataNim[i])
//            listProfile.add(profile)
//        }
//        dataImg.recycle()
//        return listProfile
//    }
//
//    private fun showRecyclerList() {
//        rvProfile.layoutManager = LinearLayoutManager(context)
//        val listProfileAdapter = ListProfileAdapter(list)
//        rvProfile.adapter = listProfileAdapter
//
//        listProfileAdapter.setOnItemClickCallback(object : ListProfileAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Profile) {
//                // Handle the item click, for example:
//                // Toast.makeText(context, "Clicked: ${data.name}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
