package com.binar.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.checkout.Data
import com.binar.projekakhir.model.checkout.PostCheckoutResponse
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(var api : ApiService) : ViewModel() {

    val _biopemesanan : MutableLiveData<Data> = MutableLiveData()

    val livebiopemesanan : LiveData<Data> = _biopemesanan


    fun postbiopemesan(token : String){
        api.postcheckout("Bearer $token").enqueue(object : Callback<PostCheckoutResponse>{
            override fun onResponse(
                call: Call<PostCheckoutResponse>,
                response: Response<PostCheckoutResponse>
            ) {
                if (response.isSuccessful) {
                   _biopemesanan.postValue(response.body()!!.data)
                } else {
                    Log.e("CheckoutViewModel", "Cannot get data1")
                }
            }

            override fun onFailure(call: Call<PostCheckoutResponse>, t: Throwable) {
                Log.e("CheckoutViewModel", "Cannot get data2")
            }

        })

    }
}