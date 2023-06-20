package com.binar.projekakhir.view.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentHomeBinding
import com.binar.projekakhir.view.adapter.FavouriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.binar.projekakhir.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private var tanggalKembali: String? = null
    private lateinit var pref : SharedPreferences
    private lateinit var HomeVm : HomeViewModel
    private lateinit var adapter: FavouriteAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HomeVm = ViewModelProvider(this).get(HomeViewModel::class.java)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        favorite()

        getSetPenumpang()


        datePickerReturn()
        getTanggalKembali()

        datareturn()
        getpilihtanggal()

        binding.passenger.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_setPenumpangFragment)
        }


    }

    private fun datePickerReturn() {
        binding.tanggal.setOnClickListener {
            val nameMonth = ArrayList<String>()
            nameMonth.add("Januari")
            nameMonth.add("Februari")
            nameMonth.add("Maret")
            nameMonth.add("April")
            nameMonth.add("Mei")
            nameMonth.add("Juni")
            nameMonth.add("Juli")
            nameMonth.add("Agustus")
            nameMonth.add("September")
            nameMonth.add("Oktober")
            nameMonth.add("November")
            nameMonth.add("Desember")

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val bulan = nameMonth[month]
                    tanggalKembali = "$dayOfMonth $bulan $year"
                    binding.tanggal.setText(tanggalKembali)
                    binding.tanggal.setTextColor(resources.getColor(R.color.black))
                },
                year, month, day,
            )
            datePickerDialog.show()

        }
    }

    private fun getTanggalKembali() {
        if (tanggalKembali == null) {
            binding.tanggal.setText("Pilih Tanggal")
        } else {
            binding.tanggal.setText(tanggalKembali)
            binding.tanggal.setTextColor(resources.getColor(R.color.black))

        }


    }

    private fun datareturn(){
        binding.pilihTanggal.setOnClickListener {
            val nameMonth = ArrayList<String>()
            nameMonth.add("Januari")
            nameMonth.add("Februari")
            nameMonth.add("Maret")
            nameMonth.add("April")
            nameMonth.add("Mei")
            nameMonth.add("Juni")
            nameMonth.add("Juli")
            nameMonth.add("Agustus")
            nameMonth.add("September")
            nameMonth.add("Oktober")
            nameMonth.add("November")
            nameMonth.add("Desember")

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val bulan = nameMonth[month]
                    tanggalKembali = "$dayOfMonth $bulan $year"
                    binding.pilihTanggal.setText(tanggalKembali)
                    binding.pilihTanggal.setTextColor(resources.getColor(R.color.black))
                },
                year, month, day,
            )
            datePickerDialog.show()
        }
    }

    private fun getpilihtanggal(){
        if (tanggalKembali == null) {
            binding.pilihTanggal.setText("Pilih Tanggal")
        } else {
            binding.pilihTanggal.setText(tanggalKembali)
            binding.pilihTanggal.setTextColor(resources.getColor(R.color.black))

        }
    }

    private fun favorite( ){
        HomeVm.getfavorite()
        HomeVm.livedatafavorite.observe(viewLifecycleOwner, Observer { favList ->
            if (favList != null) {
                val adapter = FavouriteAdapter(requireContext(), favList)
                binding.destinationfavourite.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.destinationfavourite.adapter = adapter
            }
        })

    }

    private fun getSetPenumpang(){
        val dewasa = HomeVm.getPenumpangDewasa()
        val anak = HomeVm.getPenumpangAnak()
        val bayi = HomeVm.getPenumpangBayi()
        val total = dewasa + anak + bayi
        binding.passenger.text = "$total Penumpang"
    }




}