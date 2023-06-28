package com.binar.projekakhir.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.databinding.ItemRiwayatBinding
import com.binar.projekakhir.model.history.Order
import com.binar.projekakhir.model.searchtiket.Data

class RiwayatAdapter(private val listhistory: List<Order>) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {
    class ViewHolder (val binding : ItemRiwayatBinding ) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRiwayatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.departurecity.text = listhistory[position].schedule.originAirport.city
        holder.binding.departuretgl.text = listhistory[position].schedule.departureDate
        holder.binding.departurewaktu.text = listhistory[position].schedule.departureTime
        holder.binding.arrivalcity.text = listhistory[position].schedule.destinationAirport.city
        holder.binding.arrivaltgl.text = listhistory[position].schedule.arrivedDate
        holder.binding.arrivalwaktu.text = listhistory[position].schedule.arrivedTime
        holder.binding.codeBooking.text = listhistory[position].order.orderCode
        holder.binding.classPlane.text = listhistory[position].schedule.classX.name
        holder.binding.tvPriceFlightRiwayat.text = listhistory[position].schedule.provTotalPrice.toString()

    }

    override fun getItemCount(): Int {
        return listhistory.size
    }
}