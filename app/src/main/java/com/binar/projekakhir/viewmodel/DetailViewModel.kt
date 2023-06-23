package com.binar.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.detail.Data
import com.binar.projekakhir.model.detail.GetResponseFindSchedulebyId
import com.binar.projekakhir.model.searchtiket.GetSearchTicketResponse
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var api : ApiService) : ViewModel() {

    val _detail : MutableLiveData<GetResponseFindSchedulebyId> = MutableLiveData()

    val livedetailticket : LiveData<GetResponseFindSchedulebyId> = _detail

    fun getdetailticket(id:Int) {
        api.detailticket(id).enqueue(object : Callback<GetResponseFindSchedulebyId>{
            override fun onResponse(
                call: Call<GetResponseFindSchedulebyId>,
                response: Response<GetResponseFindSchedulebyId>
            ) {
                if (response.isSuccessful){
                    _detail.value = response.body()
                }
                else{
                    Log.e("DetailViewModel", "Cannot send data")
                }
            }

            override fun onFailure(call: Call<GetResponseFindSchedulebyId>, t: Throwable) {
                Log.e("DetailViewModel", "Cannot send data 1")
            }

        })
    }

//    val detail : MutableLiveData<List<com.binar.projekakhir.model.searchtiket.Data>> = MutableLiveData()
//
//    val getlivedetail : LiveData<List<com.binar.projekakhir.model.searchtiket.Data>> = detail
//
//    fun detailticket(originAirport:String, destinationAirport: String, departureDate:String,arrivedDate:String){
//        api.getdetail(originAirport, destinationAirport, departureDate, arrivedDate).enqueue(object :Callback<GetSearchTicketResponse>{
//            override fun onResponse(
//                call: Call<GetSearchTicketResponse>,
//                response: Response<GetSearchTicketResponse>
//            ) {
//                if (response.isSuccessful){
//                    detail.value = response.body()!!.data
//                }
//                else{
//                    Log.e("DetailViewModel", "Cannot send data")
//                }
//            }
//
//            override fun onFailure(call: Call<GetSearchTicketResponse>, t: Throwable) {
//                Log.e("DetailViewModel", "Cannot send data 1")
//            }
//
//        })
//    }

}