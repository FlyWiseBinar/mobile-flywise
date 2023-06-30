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
import com.binar.projekakhir.databinding.FragmentCheckoutBinding
import com.binar.projekakhir.util.Utill
import com.binar.projekakhir.viewmodel.DetailViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment() {

    private lateinit var binding : FragmentCheckoutBinding
    private val DetailVm: DetailViewModel by viewModels()
    private lateinit var pref : SharedPreferences
    private val homeVm : HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)

//        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
         val id = homeVm.getIdTicket()
        val dewasa = homeVm.getPenumpangDewasa()
        val anak = homeVm.getPenumpangAnak()
        val bayi = homeVm.getPenumpangBayi()


        DetailVm.getdetailticket(id!!)
        DetailVm.livedetailticket.observe(viewLifecycleOwner) { detailTicket ->

            val getdetail = detailTicket.data
            if (getdetail != null){
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

                    tvFlightTime.text = "(${hourDiff}h ${minuteDiff}m)"
                    nomorseri.text = getdetail.plane.airline.airlineCode
                    tvDepartureAirport.text = getdetail.originAirport.name
                    tvDateDeparture.text = getdetail.departureDate
                    tvTimeDeparture.text = getdetail.departureTime
                    tvFlightAsal.text = getdetail.originAirport.city
                    tvFlightDestination.text = getdetail.destinationAirport.city

                    val baggageCapacity = getdetail.plane.baggageMaxCapacity.toString()
                    val baggageText = "Baggage $baggageCapacity kg"
                    baggage.text = baggageText

                    val cabiBagCap = getdetail.plane.cabinMaxCapacity.toString()
                    val cabiBagText = "Cabin baggage $cabiBagCap kg"
                    baggage.text = cabiBagText


                    val airlineName = getdetail.plane.airline.airlineName
                    val className = getdetail.classX.name

                    val planeNclassText = "$airlineName - $className"
                    nameairline.setText(planeNclassText)

                    tvTimeArrive.text = getdetail.arrivedTime
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
                if (findNavController().currentDestination!!.id == R.id.checkoutFragment) {

                    findNavController().navigate(R.id.action_checkoutFragment_to_succeesFragment)
                }

            } else {
                val fragId = findNavController().currentDestination?.id
                findNavController().popBackStack(fragId!!, true)
                findNavController().navigate(R.id.bottomSheetCekUserLogin)
            }

        }
    }



    }


