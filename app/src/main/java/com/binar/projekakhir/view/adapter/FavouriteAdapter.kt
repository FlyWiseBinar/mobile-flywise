package com.binar.projekakhir.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.databinding.ItemFavouriteBinding
import com.binar.projekakhir.model.favorite.Schedule

class FavouriteAdapter(private val context: Context,private var listFav: List<Schedule>) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.tvMaskapai.text = listFav[position].availableSeat.toString()
        holder.binding.tvPrice.text = listFav[position].adultPrice.toString()
        holder.binding.tvDestination.text = listFav[position].destinationAirport.name


    }

    override fun getItemCount(): Int {
        return listFav.size

    }
}