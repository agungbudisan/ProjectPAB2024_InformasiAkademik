package com.pab2024klsa.informasiakademik.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pab2024klsa.informasiakademik.R

class DashboardFragment : Fragment() {

    private lateinit var rvDashboard: RecyclerView
    private val list = ArrayList<DashboardButton>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvDashboard = view.findViewById(R.id.rv_dashboard)
        rvDashboard.setHasFixedSize(true)

        list.addAll(getListCars())
        showRecyclerList()

        return view
    }

    private fun getListCars(): ArrayList<DashboardButton> {
        val dataBtnTitle = resources.getStringArray(R.array.data_btn_title)
        val listDashboard = ArrayList<DashboardButton>()
        for (i in dataBtnTitle.indices) {
            val dashboard = DashboardButton(dataBtnTitle[i])
            listDashboard.add(dashboard)
        }
        return listDashboard
    }

    private fun showRecyclerList() {
        rvDashboard.layoutManager = LinearLayoutManager(requireContext())
        val listDashboardAdapter = ListDashboardAdapter(list)
        rvDashboard.adapter = listDashboardAdapter

        listDashboardAdapter.setOnItemClickCallback(object : ListDashboardAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DashboardButton) {
                showSelectedDashboardButton(data)
            }
        })
    }

    private fun showSelectedDashboardButton(dashboardButton: DashboardButton) {
        Toast.makeText(requireContext(), dashboardButton.btnTitle + " is selected", Toast.LENGTH_SHORT).show()
    }
}