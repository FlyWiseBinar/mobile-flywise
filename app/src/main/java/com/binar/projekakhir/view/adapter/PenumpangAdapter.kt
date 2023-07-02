package com.binar.projekakhir.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.databinding.ItemJenisPenumpangBinding
import com.binar.projekakhir.model.checkout.Penumpang

class PenumpangAdapter(private var listDewasa:List<Penumpang> ):RecyclerView.Adapter<PenumpangAdapter.ViewHolder>() {

    private var listener:OnItemClickListener? =null

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    inner class ViewHolder (private val binding: ItemJenisPenumpangBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(dewasa: Penumpang){
            binding.tvJenisPenumpang.text = dewasa.penumpang
            binding.cvItemJenisPenumpang.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenumpangAdapter.ViewHolder {
        val binding = ItemJenisPenumpangBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PenumpangAdapter.ViewHolder, position: Int) {
        holder.bind(listDewasa[position])
    }

    override fun getItemCount(): Int  = listDewasa.size



}