package com.binar.projekakhir.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentDetailPergiBinding
import com.binar.projekakhir.util.Utill
import com.binar.projekakhir.viewmodel.DetailViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPergiFragment : Fragment() {

    private lateinit var binding : FragmentDetailPergiBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val berandaViewModel: HomeViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailPergiBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idDeparture = berandaViewModel.getIdDep()
        detailViewModel.getdetailticket(idDeparture!!)
        detailViewModel.livedetailticket.observe(viewLifecycleOwner) { detailTicket ->

            val getDetail = detailTicket.data
            if (getDetail != null){
                binding.layoutCvDetail.visibility = View.VISIBLE
                Log.d("DetailPenerbangan","Berhasil get data")
                binding.apply {
                    val timeTakeoff = getDetail.departureTime
                    val timeLanding = getDetail.arrivedTime

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

                    txtLamaPerjalanan.text = "(${hourDiff}h ${minuteDiff}m)"
                    tvAirplaneCode.visibility = View.GONE
                    txtBandaraAwal.text = getDetail!!.originAirport.name
                    txtBandaraTujuan.text = getDetail.destinationAirport.name
                    txtKeberangkatan.text = getDetail.originAirport.city
                    txtTujuan.text = getDetail.destinationAirport.city
                    txtJamBerangkat.text = getDetail.departureTime
                    txtTanggalBerangkat.text = getDetail.departureDateTime
                    tvInformasi.text = getDetail.plane.baggageMaxCapacity.toString()
                    txtJamDatang.text = getDetail.arrivedTime
                    txtTanggalSampai.text = getDetail.arrivedDateTime
                    val price = Utill.getPriceIdFormat(getDetail.provTotalPrice!!)
                }
            } else {
                Log.e("DetailPenerbangan", "detailTicket is null")
            }

        }
    }
}