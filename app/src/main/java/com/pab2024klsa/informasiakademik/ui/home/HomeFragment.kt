package com.pab2024klsa.informasiakademik.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pab2024klsa.informasiakademik.R
import com.pab2024klsa.informasiakademik.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var rvHome: RecyclerView
    private val list = ArrayList<Home>()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rvHome = binding.rvHome
        rvHome.setHasFixedSize(true)

        if (list.isEmpty()) {
            list.addAll(getlistHome())
        }
        showRecyclerList()

        return root
    }

    private fun getlistHome(): ArrayList<Home> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDesc = resources.getStringArray(R.array.data_desc)

        val listHome = ArrayList<Home>()
        for (i in dataTitle.indices) {
            val home = Home(dataTitle[i], dataDesc[i])
            listHome.add(home)
        }

        return listHome
    }

    private fun showRecyclerList() {
        rvHome.layoutManager = LinearLayoutManager(context)
        val listHomeAdapter = ListHomeAdapter(list)
        rvHome.adapter = listHomeAdapter

//        listHomeAdapter.setOnItemClickCallback(object : ListHomeAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: College) {
//                val intent = Intent(context, DetailActivity::class.java)
//                intent.putExtra(DetailActivity.EXTRA_NAME, data.name)
//                intent.putExtra(DetailActivity.EXTRA_DESC, data.fulldesc)
//                intent.putExtra(DetailActivity.EXTRA_IMG, data.img)
//                startActivity(intent)
//            }
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}