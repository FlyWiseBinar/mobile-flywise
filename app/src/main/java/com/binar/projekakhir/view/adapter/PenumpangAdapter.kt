package com.binar.projekakhir.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.ItemBioPenumpangBinding
import com.binar.projekakhir.databinding.ItemJenisPenumpangBinding
import com.binar.projekakhir.databinding.ItemRiwayatBinding
import com.binar.projekakhir.model.checkout.Penumpang
import com.binar.projekakhir.model.checkout.request.BiodataPassenger
import com.binar.projekakhir.model.checkout.request.Passenger
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

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