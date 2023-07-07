package com.binar.projekakhir.view.ui

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentHasilPencarianFirstBinding
import com.binar.projekakhir.view.adapter.TicketAdapter
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import org.json.JSONTokener
import java.util.*

@AndroidEntryPoint
class HasilPencarianFirstFragment : Fragment() {
    private lateinit var binding : FragmentHasilPencarianFirstBinding
   private lateinit var HomeVm : HomeViewModel
    private lateinit var ticketAdapter: TicketAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHasilPencarianFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomeVm = ViewModelProvider(this).get(HomeViewModel::class.java)
        val kotaawal = HomeVm.getCityFrom()
        val kotatujuan = HomeVm.getCityTo()
        val dewasa = HomeVm.getPassengerDewasa()
        val anak = HomeVm.getPassengerAnak()
        val bayi = HomeVm.getPassengerBayi()
        val tanggalkeberangkatan = HomeVm.getDepartureDate()
        val tanggalsampai = HomeVm.getArrivedDate()
        val totalPassengers = dewasa + anak + bayi
        val seatClass = HomeVm.getNamaKelas()

        binding.tvToolbar.text = "$kotaawal <> $kotatujuan - $totalPassengers Penumpang - $seatClass"

        dateToolbarDeparture(tanggalkeberangkatan)
        dateToolbarReturn(tanggalsampai)
        departureOnly(kotaawal, kotatujuan, tanggalkeberangkatan,tanggalsampai)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun dateToolbarDeparture(dateDeparture: String?) {
        if (dateDeparture != null) {
            binding.etTanggalDeparture.setText(dateDeparture)
            binding.etTanggalDeparture.setOnClickListener {
                val calendar = Calendar.getInstance()
                val tahun = calendar.get(Calendar.YEAR)
                val bulan = calendar.get(Calendar.MONTH)
                val hari = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->

                        val tanggalPergi = "$year-${month + 1}-$dayOfMonth"
                        binding.etTanggalDeparture.setText(tanggalPergi)
                    },
                    tahun, bulan, hari,
                )
                datePickerDialog.show()
                datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                    val month = datePicker.month
                    val tahunDeparture = datePicker.year
                    val hariDeparture = datePicker.dayOfMonth
                    val tanggalDeparture = "$tahunDeparture-${month + 1}-$hariDeparture"
                    HomeVm.saveDepartureDate(tanggalDeparture)
                    binding.etTanggalDeparture.setText(tanggalDeparture)
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
            binding.etTanggalReturn.setText(dateReturn)
            binding.etTanggalReturn.setOnClickListener {
                val calendar = Calendar.getInstance()
                val tahun = calendar.get(Calendar.YEAR)
                val bulan = calendar.get(Calendar.MONTH)
                val hari = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->

                        val tanggalKembali = "$year-${month + 1}-$dayOfMonth"
                        binding.etTanggalReturn.setText(tanggalKembali)
                    },
                    tahun, bulan, hari,
                )
                datePickerDialog.show()
                datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                    val month = datePicker.month
                    val tahunReturn = datePicker.year
                    val hariReturn = datePicker.dayOfMonth
                    val tanggalReturn = "$tahunReturn-${month + 1}-$hariReturn"
                    binding.etTanggalReturn.setText(tanggalReturn)
                    HomeVm.saveDatePref(tanggalReturn)
//                    val dataPenumpang = PenumpangData("darman4","mr4","darman4@gmail.com")
//                    testViewModel.addData(dataPenumpang)
                }
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)

            }
        }
    }

    private fun departureOnly(
        cityFrom: String?,
        cityTo: String?,
        seatClass: String?,
        dateDeparture: String?
    ) {

        HomeVm.searchallticket(cityFrom!!, cityTo!!, seatClass!!, dateDeparture!!)
        HomeVm.livedatasearchallticket.observe(viewLifecycleOwner) {

//            binding.testButton.setOnClickListener {
////                val listTicket:kotlin.collections.List<DataItemTicket> = it
////                val filterTicket = filterTicketByPricingAsc(listTicket)
////                filterTicket.forEach{
////                    Log.d("Hasil Pencarian","Airline : ${it.airlines}, Price : ${it.price}")
////                }
//                val dataList = testViewModel.getDataList()
//                Log.d("Hasil Pencarian","$dataList")
//
//            }
            binding.rvDeparture.apply {
//                binding.emptyResult.visibility = View.GONE
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                ticketAdapter = TicketAdapter(it) { itemTicket ->
                    val id = itemTicket.id
                    val hargaPergi = itemTicket.provTotalPrice
                    val bundle = Bundle()
                   HomeVm.saveIdDeparture(id)
                    bundle.putInt("idDep", id)
                    bundle.putInt("pricePergi",hargaPergi)
                    findNavController().navigate(R.id.action_hasilPencarianFirstFragment_to_hasilPencarianReturnFragment,bundle)
                }
                adapter = ticketAdapter
                isNestedScrollingEnabled = false
            }
        }


    }







}