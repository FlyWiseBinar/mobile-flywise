package com.binar.projekakhir.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.databinding.ItemDestinationBinding
import com.binar.projekakhir.model.searchairport.data

class PencarianKotaAdapter(private val listairport : List<data>) : RecyclerView.Adapter<PencarianKotaAdapter.ViewHolder>() {
    class ViewHolder(val binding : ItemDestinationBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = ItemDestinationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvDestination.text = listairport[position].city
    }

    override fun getItemCount(): Int {
        return listairport.size
    }

}