package com.binar.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.searchairport.GetSearchAirportResponse
import com.binar.projekakhir.model.searchtiket.Schedule
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchAirportViewModel @Inject constructor(var api : ApiService) : ViewModel() {

    val _responsesearchairport : MutableLiveData<List<GetSearchAirportResponse>> = MutableLiveData()

    val getliveresponsesearchairport : LiveData<List<GetSearchAirportResponse>> = _responsesearchairport

    fun getsearchairport(){
        api.getsearchairport().enqueue(object :Callback<List<GetSearchAirportResponse>>{
            override fun onResponse(
                call: Call<List<GetSearchAirportResponse>>,
                response: Response<List<GetSearchAirportResponse>>
            ) {
                if (response.isSuccessful) {
                    _responsesearchairport.value = response.body()

                } else {
                    Log.e("SearchViewModel", "Cannot get data")
                }
            }

            override fun onFailure(call: Call<List<GetSearchAirportResponse>>, t: Throwable) {
                Log.e("SearchViewModel", "Cannot get data")
            }

        })
    }
}