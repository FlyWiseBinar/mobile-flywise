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
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentCheckBinding
import com.binar.projekakhir.util.Utill
import com.binar.projekakhir.viewmodel.DetailViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel


class CheckFragment : Fragment() {

    lateinit var binding : FragmentCheckBinding
    private lateinit var pref : SharedPreferences
    private val DetailVm: DetailViewModel by viewModels()

    private val homeVm : HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        val id = arguments?.getInt("id")
        DetailVm.getdetailticket(id!!)
        DetailVm.livedetailticket.observe(viewLifecycleOwner) { detailTicket ->

            val getdetail = detailTicket.data
            if (getdetail != null){
                binding.layoutContentBody.visibility = View.VISIBLE
                binding.rincianharga.visibility = View.VISIBLE
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
                    seriNo.visibility = View.GONE
                    tvDepartureAirport.text = getdetail.originAirport.name
                    tvDateDeparture.text = getdetail.departureDate
                    tvTimeDeparture.text = getdetail.departureTime
                    tvFlightAsal.text = getdetail.originAirport.city
                    tvFlightDestination.text = getdetail.destinationAirport.city
                    baggage.text = getdetail.plane.baggageMaxCapacity.toString()
                    cabinBag.text = getdetail.plane.cabinMaxCapacity.toString()
                    tvTimeArrive.text = getdetail.arrivedTime
                    tvDateArrive.text = getdetail.arrivedDate
                    tvArriveAirport.text = getdetail.destinationAirport.name
                    priceadult.text = getdetail.adultPrice.toString()
                    pricebaby.text = getdetail.babyPrice.toString()
                    //tvPriceTicket.text = getDetail.provTotalPrice.toString()
                    val price = Utill.getPriceIdFormat(getdetail.provTotalPrice)
                    tvPriceTicket.text = "$price"
                }
            } else {
                Log.e("DetailPenerbangan", "detailTicket is null")
            }



        }

//        binding.btnLanjutPayment.setOnClickListener {
//            homeVm.saveIdTicket(id)
//            if (pref.getString("token", "").toString().isNotEmpty()) {
//                if (findNavController().currentDestination!!.id == R.id.checkout_rincianFragment) {
//
//                    findNavController().navigate(R.id.action_checkoutFragment_to_paymentFragment)
//                }
//
//            } else {
//                val fragId = findNavController().currentDestination?.id
//                findNavController().popBackStack(fragId!!, true)
//                findNavController().navigate(R.id.bottomSheetCekUserLogin)
//            }
//
//        }
//    }
    }


}