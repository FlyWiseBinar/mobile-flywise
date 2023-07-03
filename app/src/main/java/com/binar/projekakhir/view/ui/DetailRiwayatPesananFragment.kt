package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentDetailRiwayatPesananBinding
import com.binar.projekakhir.util.Utill
import com.binar.projekakhir.viewmodel.DetailViewModel
import com.binar.projekakhir.viewmodel.HistoryViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRiwayatPesananFragment : Fragment() {
    lateinit var binding : FragmentDetailRiwayatPesananBinding
    private lateinit var pref : SharedPreferences
    private val homeVm : HomeViewModel by viewModels()
    private val histoVm : HistoryViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRiwayatPesananBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        binding.btnSelectFlight.setOnClickListener{
            findNavController().navigate(R.id.action_succeesFragment_to_homeFragment2)
        }

        binding.btnSelectFlight.setOnClickListener{
            findNavController().navigate(R.id.action_succeesFragment_to_homeFragment2)
        }

        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        val token = pref.getString("token", "").toString()

        val dewasa = homeVm.getPenumpangDewasa()
        val anak = homeVm.getPenumpangAnak()
        val bayi = homeVm.getPenumpangBayi()

//        val orderCode = histoVm.getOrderCode()

        val orderCode = arguments?.getString("orderCode")
//        val id = arguments?.getInt("id")
        histoVm.getdetailhistory(token, orderCode!!)
        histoVm.livedetailhistory.observe(viewLifecycleOwner) { detailHistory ->

            val getdetail = detailHistory.firstOrNull()
            if (getdetail != null){
                Log.d("DetailHistory","Berhasil get data")
                binding.apply {
                    val timeTakeoff = getdetail.schedule.departureTime
                    val timeLanding = getdetail.schedule.arrivedTime

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

                    val code = getdetail.order.orderCode
                    binding.code.text = "Booking Code: $code"

                    nomorseri.text = getdetail.schedule.plane.airline.airlineCode
                    tvDepartureAirport.text = getdetail.schedule.originAirport.name
                    tvDateDeparture.text = getdetail.schedule.departureDate

                    val departureTime = getdetail.schedule.departureTime
                    val formattedTime = departureTime.substring(0, 5)
                    tvTimeDeparture.text = formattedTime

                    val airlineName = getdetail.schedule.plane.airline.airlineName
                    val className = getdetail.schedule.classX.name
                    val planeNclassText = "$airlineName - $className"
                    planeNclass.setText(planeNclassText)

                    val arrivedTime = getdetail.schedule.arrivedTime
                    val formattedTimeArr = arrivedTime.substring(0, 5)
                    tvTimeArrive.text = formattedTimeArr

                    tvDateArrive.text = getdetail.schedule.arrivedDate
                    tvArriveAirport.text = getdetail.schedule.destinationAirport.name

                    namepassenger1.text = getdetail.order.user.fullName
                    namepassenger2.text = getdetail.order.user.fullName

                    binding.txtAdult.text = "$dewasa Adults"
                    binding.txtKids.text = "$anak Kids"
                    binding.txtBaby.text = "$bayi Baby"

                    //Jumlah Dewasa
                    val jumlahDewasa: Int = dewasa
                    val harga = getdetail.schedule.adultPrice
                    val totalHargaDewasa = jumlahDewasa * harga
                    priceadult.text = totalHargaDewasa.toString()

                    //Jumlah Anak
                    val jumlahAnak: Int = anak
                    val hargaAnak = getdetail.schedule.kidsPrice
                    val totalHargaAnak = jumlahAnak * hargaAnak
                    pricekids.text = totalHargaAnak.toString()

                    //Jumlah Bayi
                    val jumlahBayi: Int = bayi
                    val hargaBayi = getdetail.schedule.babyPrice
                    val totalHargaBayi = jumlahBayi * hargaBayi
                    pricebaby.text = totalHargaBayi.toString()

                    //Tax
                    priceTax.text = getdetail.schedule.taxPrice.toString()

                    //Total
                    val jumlahTax = getdetail.schedule.taxPrice
                    val totalHarga = totalHargaDewasa + totalHargaAnak + totalHargaBayi + jumlahTax
                    val zprice = Utill.getPriceIdFormat(totalHarga)
                    tvPriceTicket.text = zprice


                }
            } else {
                Log.e("DetailPenerbangan", "detailTicket is null")
            }




        }

//        binding.btnSelectFlight.setOnClickListener {
//            homeVm.saveIdTicket(id)
//            if (pref.getString("token", "").toString().isNotEmpty()) {
//                if (findNavController().currentDestination!!.id == R.id.succeesFragment) {
//
//                    findNavController().navigate(R.id.action_succeesFragment_to_homeFragment2)
//                }
//
//            } else {
//                val fragId = findNavController().currentDestination?.id
//                findNavController().popBackStack(fragId!!, true)
//                findNavController().navigate(R.id.bottomSheetCekUserLogin)
//            }
//
//        }
    }
}