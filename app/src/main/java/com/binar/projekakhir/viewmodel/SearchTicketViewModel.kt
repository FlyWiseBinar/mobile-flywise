package com.binar.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.auth.LoginResponse
import com.binar.projekakhir.model.searchairport.GetSearchAirportResponse
import com.binar.projekakhir.model.searchtiket.GetSearchTicketResponse
import com.binar.projekakhir.model.searchtiket.Schedule
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchTicketViewModel @Inject constructor(private val api : ApiService) : ViewModel(){

    val _responsesearchticket : MutableLiveData<List<Schedule>> = MutableLiveData()
    val responsesearchticket : LiveData<List<Schedule>> = _responsesearchticket

    fun searchticket(searchTicketResponse: GetSearchTicketResponse){
        api.getsearchtiket(searchTicketResponse).enqueue(object : Callback<List<Schedule>>{
            override fun onResponse(
                call: Call<List<Schedule>>,
                response: Response<List<Schedule>>
            ) {
                if (response.isSuccessful) {
                    _responsesearchticket.value = response.body()

                } else {
                    Log.e("UserViewModel", "Cannot get data")
                }
            }

            override fun onFailure(call: Call<List<Schedule>>, t: Throwable) {
                Log.e("UserViewModel", "Cannot get data")
            }

        })
    }

}