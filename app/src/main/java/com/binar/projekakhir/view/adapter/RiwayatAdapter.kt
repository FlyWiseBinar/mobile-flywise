package com.binar.projekakhir.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.databinding.ItemRiwayatBinding
import com.binar.projekakhir.model.history.Order
import com.binar.projekakhir.model.searchtiket.Data

class RiwayatAdapter(private val listhistory: List<Order>, private val onSelect:(Data) -> Unit) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {
    class ViewHolder (val binding : ItemRiwayatBinding ) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRiwayatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.departurecity.text = listhistory[position].schedule.originAirport.city
//        holder.binding.departuretgl.text = listhistory[position].

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}