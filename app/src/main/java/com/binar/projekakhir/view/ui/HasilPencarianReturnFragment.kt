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

    @RequiresApi(Build.VERSION_CODES.N)
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

        val idDeparture = arguments?.getInt("idDep")

        dateToolbarDeparture(dateDeparture)
        dateToolbarReturn(dateRetun)
        returnOnly(cityFrom, cityTo, dateDeparture,dateRetun)
        getDepartureTicket(idDeparture)






        binding.btnGanti.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.hasilPencarianReturnFragment) {
//                val fragId = findNavController().currentDestination?.id
//                findNavController().popBackStack(fragId!!,true)
                findNavController().navigate(R.id.action_hasilPencarianReturnFragment_to_hasilPencarianFirstFragment)
            }

        }

    }

    private fun getDepartureTicket(idDeparture: Int?) {
        detailViewModel.getdetailticket(idDeparture!!)
        detailViewModel.livedetailticket.observe(viewLifecycleOwner){it ->
            val dataItemTicket = it.data

            val timeTakeoff = dataItemTicket?.departureTime
            val timeLanding = dataItemTicket?.arrivedTime

            val takeOffSplit = timeTakeoff?.split(":")
            val landingSplit = timeLanding?.split(":")

            val takeOffHour = takeOffSplit!![0].toInt()
            val takeOffMinute = takeOffSplit!![1].toInt()

            val landingHour = landingSplit!![0].toInt()
            val landingMinute = landingSplit!![1].toInt()

            var hourDiff = landingHour - takeOffHour
            var minuteDiff = landingMinute - takeOffMinute

            if (minuteDiff < 0) {
                hourDiff -= 1
                minuteDiff += 60
            }

            if (hourDiff < 0) {
                hourDiff += 24
            }

            binding.tvDurasi.text = "${hourDiff}h ${minuteDiff}m"
            binding.tvJamKeberangkatan.text = dataItemTicket.departureTime
            binding.tvJamSampai.text = dataItemTicket.arrivedTime
            binding.tvKotaKeberangkatan.text = dataItemTicket.originAirport.city
            binding.tvKotaSampai.text = dataItemTicket.destinationAirport.city
            val getPrice = arguments?.getInt("pricePergi")
            val price = Utill.getPriceIdFormat(getPrice!!)
            binding.tvHarga.text = price
            binding.tvPesawat.text = dataItemTicket.departureDateTime
        }
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
                    findNavController().navigate(R.id.action_hasilPencarianReturnFragment_to_hasilPencarianFirstFragment)
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
                    val fragId = findNavController().currentDestination?.id
                    findNavController().popBackStack(fragId!!,true)
                    findNavController().navigate(fragId)
                }

            }
        }
    }

    private fun returnOnly(
        cityFrom: String?,
        cityTo: String?,
        seatClass: String?,
        dateReturn: String?
    ) {

        val cityToReturn = cityFrom
        val cityFromReturn = cityTo
        berandaViewModel.searchallticket(cityFromReturn!!, cityToReturn!!, seatClass!!, dateReturn!!)
        berandaViewModel.livedatasearchallticket.observe(viewLifecycleOwner) {
            binding.rvDeparture.apply {
//                binding.emptyResult.visibility = View.GONE
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                ticketAdapter = TicketAdapter(it) { itemTicket ->
                    val idReturn = itemTicket.id
                    val priceReturn = itemTicket.adultPrice
                    val idDeparture = arguments?.getInt("idDep")
                    val hargaPergi = arguments?.getInt("pricePergi")
                    val bundle = Bundle()
                    berandaViewModel.saveIdReturn(idReturn)
                    bundle.putInt("idReturn", idReturn)
                    bundle.putInt("idDeparture", idDeparture!!)
                    if (hargaPergi != null) {
                        bundle.putInt("hargaPergi",hargaPergi)
                    }
                    bundle.putInt("hargaPulang",priceReturn)
//                    findNavController().navigate(R.id.actio,bundle)
                }
                adapter = ticketAdapter
                isNestedScrollingEnabled = false
            }
        }


    }




}