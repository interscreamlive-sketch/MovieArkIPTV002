package com.tcl.movieark.ui.iptv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tcl.movieark.R
import com.tcl.movieark.model.IptvChannel

class IptvAdapter(
    private val onClick: (IptvChannel) -> Unit
) : RecyclerView.Adapter<IptvAdapter.Holder>() {

    private val list = mutableListOf<IptvChannel>()

    fun submitList(l: List<IptvChannel>) {
        list.clear()
        list.addAll(l)
        notifyDataSetChanged()
    }

    class Holder(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.txtName)
        val group: TextView = v.findViewById(R.id.txtGroup)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_iptv_channel, parent, false)
        return Holder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val c = list[position]
        holder.name.text = c.name
        holder.group.text = c.group ?: ""
        holder.itemView.setOnClickListener { onClick(c) }
    }
}
