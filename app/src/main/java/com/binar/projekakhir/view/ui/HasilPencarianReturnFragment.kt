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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentHasilPencarianReturnBinding
import com.binar.projekakhir.util.Utill
import com.binar.projekakhir.view.adapter.TicketAdapter
import com.binar.projekakhir.viewmodel.DetailViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HasilPencarianReturnFragment : Fragment() {
    private lateinit var binding : FragmentHasilPencarianReturnBinding
    private val HomeVm: HomeViewModel by viewModels()
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val kotaawal = HomeVm.getCityFrom()
        val kotatujuan = HomeVm.getCityTo()
        val dewasa = HomeVm.getPassengerDewasa()
        val anak = HomeVm.getPassengerAnak()
        val bayi = HomeVm.getPassengerBayi()
        val tanggalkeberangkatan = HomeVm.getDepartureDate()
        val tanggalpulang = HomeVm.getArrivedDate()
        val totalPassengers = dewasa + anak + bayi
        val seatClass = HomeVm.getNamaKelas()

        binding.tvToolbar.text = "$kotaawal <> $kotatujuan - $totalPassengers Penumpang - $seatClass"

        val idDeparture = arguments?.getInt("idDep")

        kalenderdeparture(tanggalkeberangkatan)
        kalenderreturn(tanggalpulang)
        getTicketTujuan(kotatujuan, kotaawal, tanggalkeberangkatan, tanggalpulang)
        getTicketKeberangkatan(idDeparture)



        binding.btnGantiticket.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.hasilPencarianReturnFragment) {
//                val fragId = findNavController().currentDestination?.id
//                findNavController().popBackStack(fragId!!,true)
                findNavController().navigate(R.id.action_hasilPencarianReturnFragment_to_hasilPencarianFirstFragment)
            }

        }

    }

    private fun getTicketKeberangkatan(idDeparture: Int?) {
        detailViewModel.getdetailticket(idDeparture!!)
        detailViewModel.livedetailticket.observe(viewLifecycleOwner){it ->
            val dataItemTicket = it.data

            val timedeparture = dataItemTicket?.departureTime
            val timearrived = dataItemTicket?.arrivedTime

            val timedepartureSplit = timedeparture?.split(":")
            val timearrivedSplit = timearrived?.split(":")

            val takeOffHour = timedepartureSplit!![0].toInt()
            val takeOffMinute = timedepartureSplit!![1].toInt()

            val landingHour = timearrivedSplit!![0].toInt()
            val landingMinute = timearrivedSplit!![1].toInt()

            var hourDiff = landingHour - takeOffHour
            var minuteDiff = landingMinute - takeOffMinute

            if (minuteDiff < 0) {
                hourDiff -= 1
                minuteDiff += 60
            }

            if (hourDiff < 0) {
                hourDiff += 24
            }

            binding.durasiperjalanan.text = "${hourDiff}h ${minuteDiff}m"
            binding.timedeparture.text = dataItemTicket.departureTime
            binding.timearrived.text = dataItemTicket.arrivedTime
            binding.kotaasal.text = dataItemTicket.originAirport.city
            binding.kotatujuan.text = dataItemTicket.destinationAirport.city
            val getPrice = arguments?.getInt("pricePergi")
            val price = Utill.getPriceIdFormat(getPrice!!)
            binding.tvHarga.text = price
            binding.namapesawat.text = dataItemTicket.plane.airline.airlineName
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun kalenderdeparture(dateDeparture: String?) {
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

                    HomeVm.saveDepartureDate(tanggalDeparture)
                    binding.etDate.setText(tanggalDeparture)
                    findNavController().navigate(R.id.action_hasilPencarianReturnFragment_to_hasilPencarianFirstFragment)
                }

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun kalenderreturn(dateReturn: String?) {
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
                    HomeVm.saveDatePref(tanggalReturn)
                    val fragId = findNavController().currentDestination?.id
                    findNavController().popBackStack(fragId!!,true)
                    findNavController().navigate(fragId)
                }

            }
        }
    }


    private fun getTicketTujuan(
        cityFrom: String?,
        cityTo: String?,
        seatClass: String?,
        dateReturn: String?
    ) {
        val cityFromReturn = cityTo
        val cityToReturn = cityFrom
        HomeVm.searchallticket(cityFromReturn!!, cityToReturn!!, seatClass!!, dateReturn!!)
        HomeVm.livedatasearchallticket.observe(viewLifecycleOwner) {
            binding.rvDeparture.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                ticketAdapter = TicketAdapter(it) { itemTicket ->
                    val idReturn = itemTicket.id
                    val priceReturn = itemTicket.adultPrice
                    val idDeparture = arguments?.getInt("idDep")
                    val hargaPergi = arguments?.getInt("pricePergi")
                    val bundle = Bundle()
                    HomeVm.saveIdReturn(idReturn)
                    bundle.putInt("idReturn", idReturn)
                    bundle.putInt("idDeparture", idDeparture!!)
                    if (hargaPergi != null) {
                        bundle.putInt("hargaPergi",hargaPergi)
                    }
                    bundle.putInt("hargaPulang",priceReturn)
                    findNavController().navigate(R.id.action_hasilPencarianReturnFragment_to_detailPenerbanganPulangPergiFragment,bundle)
                }
                adapter = ticketAdapter
                isNestedScrollingEnabled = false
            }
        }


    }




}