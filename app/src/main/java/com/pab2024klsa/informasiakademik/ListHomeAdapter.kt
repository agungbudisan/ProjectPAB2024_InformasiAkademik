package com.pab2024klsa.informasiakademik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListHomeAdapter(private val listHome: ArrayList<Home>) : RecyclerView.Adapter<ListHomeAdapter.ListViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: Home)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_home, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listHome.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val home = listHome[position]
        holder.tvTitle.text = home.title
        holder.tvDesc.text = home.desc

//        holder.itemView.setOnClickListener {
//            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
//                putExtra(DetailActivity.EXTRA_NAME, college.name)
//                putExtra(DetailActivity.EXTRA_DESC, college.fulldesc)
//                putExtra(DetailActivity.EXTRA_IMG, college.img)
//                putExtra(DetailActivity.EXTRA_LOCT, college.location)
//                putExtra(DetailActivity.EXTRA_YEAR, college.year)
//            }
//            holder.itemView.context.startActivity(intent)
//        }
    }
}