package com.binar.projekakhir.view.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentHasilPencarianBinding
import com.binar.projekakhir.view.adapter.FilterTicketAdapter
import com.binar.projekakhir.view.adapter.TicketAdapter
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HasilPencarianFragment : Fragment() {
    private lateinit var binding : FragmentHasilPencarianBinding
    private val HomeVm : HomeViewModel by viewModels()
    private lateinit var ticketAdapter: TicketAdapter
    private lateinit var filterTicketAdapter: FilterTicketAdapter
    private var tanggalPergi:String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHasilPencarianBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val cityFrom = HomeVm.getCityFrom()
        val cityTo = HomeVm.getCityTo()
        val dewasa = HomeVm.getPenumpangDewasa()
        val departure = HomeVm.getDepartureDate()
        val arrived = HomeVm.getArrivedDate()
        val order = HomeVm.getorder()
        val anak = HomeVm.getPenumpangAnak()
        val bayi = HomeVm.getPenumpangBayi()
        val totalPassengers = dewasa + anak + bayi
        val seatClass = HomeVm.getNamaKelas()
        binding.tvToolbar.text = "$cityFrom < > $cityTo - $totalPassengers Penumpang - $seatClass"

        departureOnly(cityFrom, cityTo, departure,arrived)

        dateToolbar(departure)

//        HomeVm.searchallticket(cityFrom!!, cityTo!!,departure!!,arrived!!)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_hasilPencarianFragment_to_homeFragment2)
        }

        binding.btnFilter.setOnClickListener {
            filterharga(cityFrom,cityTo,departure,arrived,order)
        }

        binding.btnFilterHarga.setOnClickListener {
            val modalBottomSheet = FilterHarga()
            modalBottomSheet.show(requireFragmentManager(),FilterHarga.TAG)
     //     val intent = Intent(this, GetFilterPrice::class.java)
      //      findNavController().navigate(R.id.action_hasilPencarianFragment_to_dialogFilter)
        }



//        HomeVm.livedatasearchallticket.observe(viewLifecycleOwner){
//            binding.rvListHari.apply {
//                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
//                ticketAdapter = TicketAdapter(it){itemTicket ->
//                    val id = itemTicket.id.toString()
//                    val bundle = Bundle()
//                    bundle.putString("id",id)
//                    findNavController().navigate(R.id.action_hasilPencarianFragment_to_detailNonLoginFragment,bundle)
//                }
//                adapter  = ticketAdapter
//            }
//        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun dateToolbar(dateDeparture: String?) {
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

                        tanggalPergi = "$year-${month + 1}-$dayOfMonth"
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
                    HomeVm.saveDepartureDate(tanggalDeparture)
                    findNavController().navigate(R.id.hasilPencarianFragment)
                }
//                    datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
//                        .setTextColor(resources.getColor(R.color.darkblue_05))
//                    datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
//                        .setTextColor(resources.getColor(R.color.darkblue_05))
            }
        }
    }

    private fun departureOnly(
        cityFrom: String?,
        cityTo: String?,
        dateDeparture: String?,
        arrivedDate:String?
    ) {
        HomeVm.searchallticket(cityFrom!!,cityTo!!,dateDeparture!!,arrivedDate!!)
        HomeVm.livedatasearchallticket.observe(viewLifecycleOwner){
            binding.rvListHari.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
                ticketAdapter = TicketAdapter(it){itemTicket ->
                    val id = itemTicket.id
                    val bundle = Bundle()
                    bundle.putInt("id",id)
                    findNavController().navigate(R.id.action_hasilPencarianFragment_to_detailNonLoginFragment,bundle)
                }
                adapter  = ticketAdapter
            }
        }
    }

    private fun filterharga(
        cityFrom: String?,
        cityTo: String?,
        dateDeparture: String?,
        arrivedDate:String?,
        order:String?
    ){
        HomeVm.getfilterprice(cityFrom!!,cityTo!!,dateDeparture!!,arrivedDate!!,order!!)
        HomeVm.livedatafilterprice.observe(viewLifecycleOwner){
            binding.rvDataFlight.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
                filterTicketAdapter = FilterTicketAdapter(it){ticket ->
                    val id = ticket.id
                    val bundle = Bundle()
                    bundle.putInt("id",id)
                    findNavController().navigate(R.id.action_hasilPencarianFragment_to_detailNonLoginFragment,bundle)
                }
                adapter  = filterTicketAdapter
            }
        }
    }


}


