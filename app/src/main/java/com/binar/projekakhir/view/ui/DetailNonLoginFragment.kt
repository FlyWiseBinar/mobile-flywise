package com.binar.projekakhir.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentDetailNonLoginBinding
import com.binar.projekakhir.util.Utill
import com.binar.projekakhir.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNonLoginFragment : Fragment() {

    private lateinit var binding : FragmentDetailNonLoginBinding
    private val DetailVm : DetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailNonLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("id")
        DetailVm.getdetailticket(id!!)
        DetailVm.livedetailticket.observe(viewLifecycleOwner) {detail->
            val getdetail = detail.data
//            val id = getdetail.id.toString()
//            val bundle = Bundle()
//            bundle.getString("id", id)
            if (getdetail != null){
                binding.layoutDetail.visibility = View.VISIBLE
                binding.totalTicket.visibility = View.VISIBLE
                Log.d("DetailPenerbangan","Berhasil dong")
                binding.apply {
                    nomorseri.visibility = View.GONE
                    val idpenerbangan = detail.data.id
                    idFlight.text = idpenerbangan.toString()
                    tvDepartureAirport.text = getdetail.originAirport.name
                    tvDateDeparture.text = getdetail.departureDate
                    tvTimeDeparture.text = getdetail.departureTime
                    baggage.text = getdetail.plane.baggageMaxCapacity.toString()
                    cabinbaggage.text = getdetail.plane.cabinMaxCapacity.toString()
                    tvTimeArrive.text = getdetail.arrivedTime
                    tvDateArrive.text = getdetail.arrivedDate
                    tvArriveAirport.text = getdetail.destinationAirport.name
                    val price = Utill.getPriceIdFormat(getdetail.provTotalPrice)
                    tvPriceTicket.text = "$price"


                }
            }else {
                Log.e("DetailPenerbangan", "detailTicket is null")
            }
        }

    }

//    private fun detail(  cityFrom: String?,
//                         cityTo: String?,
//                         dateDeparture: String?,
//                         arrivedDate:String?){
//        DetailVm.detailticket(cityFrom!!, cityTo!!, dateDeparture!!, arrivedDate!!)
//        DetailVm.getlivedetail.observe(viewLifecycleOwner){detail ->
//
//            val id =
//            val bundle = Bundle()
//            bundle.getString("id", id)
//            if (getdetail != null){
//                binding.layoutDetail.visibility = View.VISIBLE
//                binding.totalTicket.visibility = View.VISIBLE
//                Log.d("DetailPenerbangan","Berhasil dong")
//                binding.apply {
//                    nomorseri.visibility = View.GONE
//                    val idpenerbangan = detail.data
//                    idFlight.text = idpenerbangan.toString()
//                    tvDepartureAirport.text = detail.originAirport.name
//                    tvDateDeparture.text = getdetail.departureDate
//                    tvTimeDeparture.text = getdetail.departureTime
//                    baggage.text = getdetail.plane.baggageMaxCapacity.toString()
//                    cabinbaggage.text = getdetail.plane.cabinMaxCapacity.toString()
//                    tvTimeArrive.text = getdetail.arrivedTime
//                    tvDateArrive.text = getdetail.arrivedDate
//                    tvArriveAirport.text = getdetail.destinationAirport.name
//                    val price = Utill.getPriceIdFormat(getdetail.provTotalPrice)
//                    tvPriceTicket.text = "$price"
//
//
//                }
//            }else {
//                Log.e("DetailPenerbangan", "detailTicket is null")
//            }
//
//        }
//    }


}