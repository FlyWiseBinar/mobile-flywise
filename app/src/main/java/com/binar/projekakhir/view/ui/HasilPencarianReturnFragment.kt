package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentHasilPencarianReturnBinding
import com.binar.projekakhir.view.adapter.TicketAdapter
import com.binar.projekakhir.viewmodel.DetailViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel


class HasilPencarianReturnFragment : Fragment() {
    private lateinit var binding : FragmentHasilPencarianReturnBinding
    private val berandaViewModel: HomeViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var ticketAdapter: TicketAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHasilPencarianReturnBinding.inflate(inflater,container,false)
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

//        binding..text = "$cityFrom <> $cityTo - $totalPassengers Penumpang - $seatClass"

        val idDeparture = arguments?.getString("idDep")

    }
}