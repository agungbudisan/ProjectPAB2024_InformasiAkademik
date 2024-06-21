package com.pab2024klsa.informasiakademik.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pab2024klsa.informasiakademik.R

class ListProfileAdapter(private val listProfile: ArrayList<Profile>) : RecyclerView.Adapter<ListProfileAdapter.ListViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: Profile)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvNim: TextView = itemView.findViewById(R.id.tv_item_nim)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_profile, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listProfile.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val profile = listProfile[position]
        holder.imgPhoto.setImageResource(profile.img)
        holder.tvName.text = profile.name
        holder.tvNim.text = profile.nim

        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(profile)
        }
    }
}