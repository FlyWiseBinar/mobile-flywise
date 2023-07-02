package com.binar.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.paymentcreate.GetPaymentCreateResponse
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class PaymentViewModel @Inject constructor(var api : ApiService) : ViewModel() {
    val _checkoutpayment : MutableLiveData<GetPaymentCreateResponse> = MutableLiveData()

    val livepayment : LiveData<GetPaymentCreateResponse> = _checkoutpayment

    fun postpayment(token : String, paymentCode : String, paymentTypeId : Int){
        api.postpayment("Bearer $token", paymentCode, paymentTypeId).enqueue(object :Callback<GetPaymentCreateResponse>{
            override fun onResponse(
                call: Call<GetPaymentCreateResponse>,
                response: Response<GetPaymentCreateResponse>
            ) {
                if (response.isSuccessful){
                    _checkoutpayment.value = response.body()
                } else {
                    Log.e("PaymentViewModel","${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<GetPaymentCreateResponse>, t: Throwable) {
                Log.e("PaymentViewModel", "Cannot get data2")
            }

        })
    }
}