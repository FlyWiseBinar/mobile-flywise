package com.binar.projekakhir.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.databinding.ItemFilterTicketBinding
import com.binar.projekakhir.databinding.ItemTicketBinding
import com.binar.projekakhir.model.filterprice.Data
import com.binar.projekakhir.util.Utill

class FilterTicketAdapter(private val listticket: List<Data>, private val onSelect:(Data) -> Unit) : RecyclerView.Adapter<FilterTicketAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemFilterTicketBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFilterTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvJamberangkat.text = listticket[position].departureTime
        holder.binding.lokasiberangkat.text = listticket[position].originAirport.airportCode
        holder.binding.durasikeberangkatan.text = listticket[position].durationInSecond.toString()
        holder.binding.tvJamkedatangan.text = listticket[position].arrivedTime
        holder.binding.lokasikedatangan.text = listticket[position].destinationAirport.airportCode
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