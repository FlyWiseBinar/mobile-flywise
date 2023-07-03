package com.binar.projekakhir.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.history.GetHistoryResponse
import com.binar.projekakhir.model.history.Order
import com.binar.projekakhir.model.history.Schedule
import com.binar.projekakhir.model.historybyorder.ResponseHistoryByCode
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(var api : ApiService, private val sharedPreferences: SharedPreferences) : ViewModel() {
    val _history : MutableLiveData<List<Order>> = MutableLiveData()
    val livehistory : LiveData<List<Order>> = _history

    fun gethistory(token : String){
        api.gethistory("Bearer $token").enqueue(object :Callback<GetHistoryResponse>{
            override fun onResponse(
                call: Call<GetHistoryResponse>,
                response: Response<GetHistoryResponse>
            ) {
                if (response.isSuccessful) {
                    _history.postValue(response.body()!!.orders)
                } else {
                    Log.e("History ViewModel", "Cannot get data1")
                }
            }

            override fun onFailure(call: Call<GetHistoryResponse>, t: Throwable) {
                Log.e("History ViewModel", "Cannot get data2")
            }

        })

    }

    val _detailhistory : MutableLiveData<List<com.binar.projekakhir.model.historybyorder.Order>> = MutableLiveData()
    val livedetailhistory : LiveData<List<com.binar.projekakhir.model.historybyorder.Order>> = _detailhistory
    fun getdetailhistory(token: String,orderCode: String){
        api.getdetailhistory("Bearer $token",orderCode).enqueue(object :Callback<ResponseHistoryByCode>{
            override fun onResponse(
                call: Call<ResponseHistoryByCode>,
                response: Response<ResponseHistoryByCode>
            ) {
                if (response.isSuccessful) {
                    _detailhistory.postValue(response.body()!!.orders)
                } else {
                    Log.e("Detail HisViewModel", "Cannot get data1")
                }
            }

            override fun onFailure(call: Call<ResponseHistoryByCode>, t: Throwable) {
                Log.e("Detail HisViewModel", "Cannot get data : onFailure", t)
            }

        })

    }


//    fun saveOrderCode(orderCode:String){
//        val editor =  sharedPreferences.edit()
//        editor.putString("orderCode",orderCode)
//        editor.apply()
//    }

//    fun getOrderCode():String?{
//        return sharedPreferences.getString("orderCode", orderCode)
//    }



}