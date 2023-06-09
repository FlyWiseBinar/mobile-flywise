package com.binar.projekakhir.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.binar.projekakhir.databinding.FragmentDetailPenerbanganPulangBinding
import com.binar.projekakhir.util.Utill
import com.binar.projekakhir.viewmodel.DetailViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPenerbanganPulangFragment : Fragment() {

    private lateinit var binding : FragmentDetailPenerbanganPulangBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val berandaViewModel:HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailPenerbanganPulangBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idReturn = berandaViewModel.getIdReturn()
        detailViewModel.getdetailticket(idReturn!!)
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
                    txtBandaraAwal.text = getDetail!!.destinationAirport.name
                    txtBandaraTujuan.text = getDetail.originAirport.name

                    txtKeberangkatan.text = getDetail.destinationAirport.city
                    txtTujuan.text = getDetail.originAirport.city

                    txtJamBerangkat.text = getDetail.departureTime
                    tvPesawat.text = getDetail.plane.airline.airlineName
                    txtTanggalBerangkat.text = getDetail.departureDate
                    tvAirplaneCode.text = getDetail.plane.airline.airlineCode
                    tvInformasi.text = getDetail.plane.baggageMaxCapacity.toString()
                    txtJamDatang.text = getDetail.arrivedTime
                    txtTanggalSampai.text = getDetail.arrivedDate
                    val price = Utill.getPriceIdFormat(getDetail.provTotalPrice!!)
                }
            } else {
                Log.e("DetailPenerbangan", "detailTicket is null")
            }

        }
    }
    }


