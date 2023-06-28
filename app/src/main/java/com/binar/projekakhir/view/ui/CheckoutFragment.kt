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

        val id = arguments?.getInt("id")
        DetailVm.getdetailticket(id!!)

        DetailVm.livedetailticket.observe(viewLifecycleOwner) { detailTicket ->

            val getdetail = detailTicket.data
            if (getdetail != null){
                binding.layoutrinciancheckout.visibility = View.VISIBLE
                binding.layoutrincianharga.visibility = View.VISIBLE
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
                    nomorseri.visibility = View.GONE
                    tvDepartureAirport.text = getdetail.originAirport.name
                    nameairline.text = getdetail.plane.airline.airlineName
                    tvDateDeparture.text = getdetail.departureDate
                    tvTimeDeparture.text = getdetail.departureTime
                    tvFlightAsal.text = getdetail.originAirport.city
                    tvFlightDestination.text = getdetail.destinationAirport.city
                    baggage.text = getdetail.plane.baggageMaxCapacity.toString()
                    cabinbaggage.text = getdetail.plane.cabinMaxCapacity.toString()
                    tvTimeArrive.text = getdetail.arrivedTime
                    tvDateArrive.text = getdetail.arrivedDate
                    tvArriveAirport.text = getdetail.destinationAirport.name
                    val price = Utill.getPriceIdFormat(getdetail.provTotalPrice)
                    tvPriceTicket.text = "$price"
                }
            } else {
                Log.e("DetailPenerbangan", "detailTicket is null")
            }




        }
    }


}