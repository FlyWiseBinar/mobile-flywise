package com.binar.projekakhir.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.databinding.ItemFavouriteBinding
import com.binar.projekakhir.model.favorite.Data

class FavouriteAdapter( private val listFav: List<Data>):RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvMaskapai.text = listFav[position].plane.airline.airlineName
        holder.binding.tvPrice.text = listFav[position].provTotalPrice.toString()
        holder.binding.tvDestination.text = listFav[position].destinationAirport.name
        holder.binding.tvDeparture.text = listFav[position].originAirport.name
        holder.binding.tvTanggalFavorit.text = listFav[position].arrivedDate
    }

    override fun getItemCount(): Int {
        return listFav.size
    }
}