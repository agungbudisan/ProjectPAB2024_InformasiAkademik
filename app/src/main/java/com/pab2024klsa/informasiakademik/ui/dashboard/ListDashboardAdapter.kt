package com.pab2024klsa.informasiakademik.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pab2024klsa.informasiakademik.R

class ListDashboardAdapter(private val listDashboard: ArrayList<DashboardButton>) : RecyclerView.Adapter<ListDashboardAdapter.ListViewHolder>() {
    interface OnItemClickCallback {
        fun onItemClicked(data: DashboardButton)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBtnTitle: TextView = itemView.findViewById(R.id.tv_btn_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_dashboard, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listDashboard.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (btnTitle) = listDashboard[position]
        holder.tvBtnTitle.text = btnTitle
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listDashboard[holder.adapterPosition]) }
    }
}