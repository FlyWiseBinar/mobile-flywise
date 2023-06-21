package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentHasilPencarianBinding
import com.binar.projekakhir.view.adapter.TicketAdapter
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HasilPencarianFragment : Fragment() {
    private lateinit var binding : FragmentHasilPencarianBinding
    private val HomeVm : HomeViewModel by viewModels()
    private lateinit var ticketAdapter: TicketAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHasilPencarianBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val cityFrom = HomeVm.getCityFrom()
        val cityTo = HomeVm.getCityTo()
        val dewasa = HomeVm.getPenumpangDewasa()
        val departure = HomeVm.getDepartureDate()
        val arrived = HomeVm.getArrivedDate()
        val anak = HomeVm.getPenumpangAnak()
        val bayi = HomeVm.getPenumpangBayi()
        val totalPassengers = dewasa + anak + bayi
        val seatClass = HomeVm.getNamaKelas()
        binding.tvToolbar.text = "$cityFrom < > $cityTo - $totalPassengers Penumpang - $seatClass"

        HomeVm.searchallticket(cityFrom!!, cityTo!!,departure!!,arrived!!)
        HomeVm.livedatafavorite.observe(viewLifecycleOwner){
            binding.rvListHari.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
                ticketAdapter = TicketAdapter(it){itemTicket ->
                    val id = itemTicket.id.toString()
                    val bundle = Bundle()
                    bundle.putString("id",id)
                    findNavController().navigate(R.id.action_hasilPencarianFragment_to_detailNonLoginFragment,bundle)
                }
                adapter  = ticketAdapter
            }
        }

    }
    }


