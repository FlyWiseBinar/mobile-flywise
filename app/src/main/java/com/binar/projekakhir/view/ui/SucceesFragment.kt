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
import com.binar.projekakhir.databinding.FragmentSucceesBinding
import com.binar.projekakhir.util.Utill
import com.binar.projekakhir.viewmodel.DetailViewModel
import com.binar.projekakhir.viewmodel.HistoryViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SucceesFragment : Fragment() {
    lateinit var binding : FragmentSucceesBinding
    private val DetailVm: DetailViewModel by viewModels()
    private lateinit var pref : SharedPreferences
    private val homeVm : HomeViewModel by viewModels()
    private val histoVm : HistoryViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSucceesBinding.inflate(inflater, container,false)
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


        val id = homeVm.getTicketId()
        val dewasa = homeVm.getPassengerDewasa()
        val anak = homeVm.getPassengerAnak()
        val bayi = homeVm.getPassengerBayi()

        val nama = homeVm.getnama()

        binding.namepenumpang1.text = nama
        binding.namepenumpang2.text = nama


        DetailVm.getdetailticket(id!!)
        DetailVm.livedetailticket.observe(viewLifecycleOwner) { detailTicket ->

            val getdetail = detailTicket.data
            if (getdetail != null){
//                binding.layoutDetail.visibility = View.VISIBLE
                Log.d("DetailPenerbangan","Berhasil get data")
                binding.apply {
                    val timeTakeoff = getdetail.departureTime
                    val timeLanding = getdetail.arrivedTime

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

                    nomorseri.text = getdetail.plane.airline.airlineCode
                    tvDepartureAirport.text = getdetail.originAirport.name
                    tvDateDeparture.text = getdetail.departureDate

                    val departureTime = getdetail.departureTime
                    val formattedTime = departureTime.substring(0, 5) // Mengambil karakter dari indeks 0 hingga 4 (jam dan menit)
                    tvTimeDeparture.text = formattedTime

                    val airlineName = getdetail.plane.airline.airlineName
                    val className = getdetail.classX.name
                    val planeNclassText = "$airlineName - $className"
                    planeNclass.setText(planeNclassText)

                    val arriveTime = getdetail.arrivedTime
                    val formattedTimeArr = arriveTime.substring(0, 5)
                    tvTimeArrive.text = formattedTimeArr

                    tvDateArrive.text = getdetail.arrivedDate
                    tvArriveAirport.text = getdetail.destinationAirport.name

                    binding.txtAdult.text = "$dewasa Adults"
                    binding.txtKids.text = "$anak Kids"
                    binding.txtBaby.text = "$bayi Baby"

                    //Jumlah Dewasa
                    val jumlahDewasa: Int = dewasa
                    val harga = getdetail.adultPrice
                    val totalHargaDewasa = jumlahDewasa * harga
                    priceadult.text = totalHargaDewasa.toString()

                    //Jumlah Anak
                    val jumlahAnak: Int = anak
                    val hargaAnak = getdetail.kidsPrice
                    val totalHargaAnak = jumlahAnak * hargaAnak
                    pricekids.text = totalHargaAnak.toString()

                    //Jumlah Bayi
                    val jumlahBayi: Int = bayi
                    val hargaBayi = getdetail.babyPrice
                    val totalHargaBayi = jumlahBayi * hargaBayi
                    pricebaby.text = totalHargaBayi.toString()

                    //Tax
                    priceTax.text = getdetail.taxPrice.toString()

                    //Total
                    val jumlahTax = getdetail.taxPrice
                    val totalHarga = totalHargaDewasa + totalHargaAnak + totalHargaBayi + jumlahTax
                    val zprice = Utill.getPriceIdFormat(totalHarga)
                    tvPriceTicket.text = zprice


                }
            } else {
                Log.e("DetailPenerbangan", "detailTicket is null")
            }




        }

        binding.btnSelectFlight.setOnClickListener {
            homeVm.saveIdTicket(id)
            if (pref.getString("token", "").toString().isNotEmpty()) {
                if (findNavController().currentDestination!!.id == R.id.succeesFragment) {

                    findNavController().navigate(R.id.action_succeesFragment_to_homeFragment2)
                }

            } else {
                val fragId = findNavController().currentDestination?.id
                findNavController().popBackStack(fragId!!, true)
                findNavController().navigate(R.id.bottomSheetCekUserLogin)
            }

        }
    }
    }


