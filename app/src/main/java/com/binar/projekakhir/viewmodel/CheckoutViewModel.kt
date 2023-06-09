package com.binar.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.checkout.PenumpangRoundTripRequest
import com.binar.projekakhir.model.checkout.PostCheckoutResponse
import com.binar.projekakhir.model.paymentcreate.GetPaymentCreateResponse
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(var api : ApiService) : ViewModel() {

    val _biopemesanan : MutableLiveData<PostCheckoutResponse> = MutableLiveData()

    val livebiopemesanan : LiveData<PostCheckoutResponse> = _biopemesanan


    fun postbiopemesan(token : String, data : PenumpangRoundTripRequest){
        api.postcheckout("Bearer $token", data).enqueue(object : Callback<PostCheckoutResponse>{
            override fun onResponse(
                call: Call<PostCheckoutResponse>,
                response: Response<PostCheckoutResponse>
            ) {
                if (response.isSuccessful){
                    _biopemesanan.value = response.body()
                } else {
                    Log.e("BiodataViewModel","${response.errorBody()?.string()}")
                }

            }

            override fun onFailure(call: Call<PostCheckoutResponse>, t: Throwable) {
                Log.e("CheckoutViewModel", "Cannot get data2")
            }

        })

    }







}