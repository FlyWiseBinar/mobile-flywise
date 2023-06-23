package com.binar.projekakhir.view.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.ItemDestinationBinding
import com.binar.projekakhir.model.searchairport.data

class PencarianFromAdapter(private val context: Context, private val listairport : List<data>) : RecyclerView.Adapter<PencarianFromAdapter.ViewHolder>(){
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefsFrom", Context.MODE_PRIVATE)
    inner class ViewHolder(val binding: ItemDestinationBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindSearch(item: data, sharedPreferences: SharedPreferences) {
            binding.tvDestination.setOnClickListener {
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("keyFrom", item.city)
                editor.apply()

                val navController = Navigation.findNavController(binding.root)
                navController.previousBackStackEntry?.savedStateHandle?.set("selected_destination", item.city)
                navController.navigate(R.id.action_pilihDestinasiFromFragment_to_homeFragment2)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val item = listairport[position]
        holder.bindSearch(item, sharedPreferences)
        holder.binding.tvDestination.text = item.city

        holder.itemView.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            // Menyimpan data ke SharedPreferences
            editor.putString("keyFrom", item.city)
            editor.apply()

            Navigation.findNavController(it).navigate(R.id.action_pilihDestinasiFromFragment_to_homeFragment2)
        }
    }

    override fun getItemCount(): Int {
        return listairport.size
    }

}