package com.binar.projekakhir.view.ui

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentHasilPencarianBinding
import com.binar.projekakhir.databinding.FragmentHasilPencarianFirstBinding
import com.binar.projekakhir.view.adapter.TicketAdapter
import com.binar.projekakhir.viewmodel.HomeViewModel
import java.util.*


class HasilPencarianFirstFragment : Fragment() {
    private lateinit var binding : FragmentHasilPencarianFirstBinding
    private val berandaViewModel: HomeViewModel by viewModels()
    private lateinit var ticketAdapter: TicketAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHasilPencarianFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityFrom = berandaViewModel.getCityFrom()
        val cityTo = berandaViewModel.getCityTo()
        val dewasa = berandaViewModel.getPenumpangDewasa()
        val anak = berandaViewModel.getPenumpangAnak()
        val bayi = berandaViewModel.getPenumpangBayi()
        val dateDeparture = berandaViewModel.getDepartureDate()
        val dateRetun = berandaViewModel.getArrivedDate()
        val totalPassengers = dewasa + anak + bayi
        val seatClass = berandaViewModel.getNamaKelas()

        binding.tvToolbar.text = "$cityFrom <> $cityTo - $totalPassengers Penumpang - $seatClass"
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun dateToolbarDeparture(dateDeparture: String?) {
        if (dateDeparture != null) {
            binding.etDate.setText(dateDeparture)
            binding.etDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->

                        val tanggalPergi = "$year-${month + 1}-$dayOfMonth"
                        binding.etDate.setText(tanggalPergi)
                    },
                    year, month, day,
                )
                datePickerDialog.show()
                datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                    val month = datePicker.month
                    val tahunDeparture = datePicker.year
                    val hariDeparture = datePicker.dayOfMonth
                    val tanggalDeparture = "$tahunDeparture-${month + 1}-$hariDeparture"
                    berandaViewModel.saveDepartureDate(tanggalDeparture)
                    binding.etDate.setText(tanggalDeparture)
                    val fragId = findNavController().currentDestination?.id
                    findNavController().popBackStack(fragId!!,true)
                    findNavController().navigate(fragId)
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun dateToolbarReturn(dateReturn: String?) {
        if (dateReturn != null) {
            binding.etDateReturn.setText(dateReturn)
            binding.etDateReturn.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->

                        val tanggalKembali = "$year-${month + 1}-$dayOfMonth"
                        binding.etDateReturn.setText(tanggalKembali)
                    },
                    year, month, day,
                )
                datePickerDialog.show()
                datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                    val month = datePicker.month
                    val tahunDeparture = datePicker.year
                    val hariDeparture = datePicker.dayOfMonth
                    val tanggalReturn = "$tahunDeparture-${month + 1}-$hariDeparture"
                    binding.etDateReturn.setText(tanggalReturn)
                    berandaViewModel.saveDatePref(tanggalReturn)
//                    val dataPenumpang = PenumpangData("darman4","mr4","darman4@gmail.com")
//                    testViewModel.addData(dataPenumpang)
                }
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)

            }
        }
    }





}