package com.binar.projekakhir.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.databinding.ItemTicketBinding
import com.binar.projekakhir.model.searchtiket.Data
import com.binar.projekakhir.util.Utill

class TicketAdapter(private val listticket: List<Data>, private val onSelect:(Data) -> Unit) : RecyclerView.Adapter<TicketAdapter.ViewHolder>() {
    class ViewHolder(val binding : ItemTicketBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val departureTime = listticket[position].departureTime
        val formattedTime = departureTime.substring(0, 5)
        holder.binding.tvJamberangkat.text = formattedTime

        val arriveTime = listticket[position].arrivedTime
        val formattedTimeArr = arriveTime.substring(0, 5)
        holder.binding.tvJamkedatangan.text = formattedTimeArr

        holder.binding.lokasiberangkat.text = listticket[position].originAirport.airportCode
        holder.binding.durasikeberangkatan.text = listticket[position].durationInSecond.toString()
        holder.binding.lokasikedatangan.text = listticket[position].destinationAirport.airportCode
        holder.binding.namapesawat.text = listticket[position].plane.airline.airlineName
        val price = Utill.getPriceIdFormat(listticket[position].provTotalPrice)
        holder.binding.tvPriceFlight.text = price
        holder.binding.hasilPencarian.setOnClickListener {
            onSelect(listticket[position])
        }
    }

    override fun getItemCount(): Int {
       return listticket.size
    }
}