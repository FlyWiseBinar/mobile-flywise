package com.binar.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.auth.Data
import com.binar.projekakhir.model.auth.LoginResponse
import com.binar.projekakhir.model.auth.login.LoginBody
import com.binar.projekakhir.model.auth.ResponseRegister
import com.binar.projekakhir.model.auth.otp.SendOtpResponse
import com.binar.projekakhir.model.auth.resetpassword.ResetPassPost
import com.binar.projekakhir.model.auth.resetpassword.UpdateProfilePost
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val api : ApiService) : ViewModel() {

    private val _responselogin : MutableLiveData<LoginResponse> = MutableLiveData()
    val responselogin : LiveData<LoginResponse> = _responselogin

    private val _responseregist: MutableLiveData<ResponseRegister> = MutableLiveData()
    val responseRegister: LiveData<ResponseRegister> = _responseregist


    var livedataresetpasssendotp : MutableLiveData<List<Data>> = MutableLiveData()

    fun getlivedataresetpasssendotp():MutableLiveData<List<Data>>{
        return livedataresetpasssendotp
    }

    var livedataresetpass : MutableLiveData<List<Data>> = MutableLiveData()

    fun getliveresetpass():MutableLiveData<List<Data>>{
        return livedataresetpass
    }

    var livedataupdateprofile : MutableLiveData<List<Data>> = MutableLiveData()

    fun getlivedataupdateprofile() : MutableLiveData<List<Data>>{
        return livedataupdateprofile
    }


    fun postlogin(loginBody: LoginBody) {
       api.login(loginBody).enqueue(object : Callback<LoginResponse>{
           override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
               if (response.isSuccessful) {
                   _responselogin.value = response.body()

               } else {
                   Log.e("UserViewModel", "Cannot get data")
               }
           }

           override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
               Log.e("UserViewModel", "Cannot get data")
           }

       })


    }


    fun postregist(fullName: String, email: String, password: String, telephone: String) {
        api.register(fullName, email, password, telephone)
            .enqueue(object : Callback<ResponseRegister> {
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    if (response.isSuccessful) {
                        _responseregist.value = response.body()

                    } else {
                        Log.e("UserViewModel", "Cannot get data")
                    }
                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    Log.e("UserViewModel", "Cannot get data")
                }

            })

    }





    fun resetpasssendotp(sendOtpResponse: SendOtpResponse){
        api.resetsendotp(sendOtpResponse).enqueue(object : Callback<List<Data>>{
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                if (response.isSuccessful) {
                    livedataresetpasssendotp.postValue(response.body())
                } else {
                    Log.e("UserViewModel", "Cannot get data")
                }
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                Log.e("UserViewModel", "Cannot get data")
            }

        })
    }

    fun resetpass(resetpass: ResetPassPost){
        api.putresetpassword(resetpass).enqueue(object : Callback<List<Data>>{
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                if (response.isSuccessful) {
                    livedataresetpass.postValue(response.body())
                } else {
                    Log.e("UserViewModel", "Cannot get data")
                }

            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                Log.e("UserViewModel", "Cannot get data")
            }

        })
    }

    fun updateprofile(token : String, updateprofile:UpdateProfilePost){
        api.putupdateprofile("Bearer $token",updateprofile).enqueue(object : Callback<List<Data>>{
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                if (response.isSuccessful) {
                    livedataupdateprofile.postValue(response.body())
                } else {
                    Log.e("UserViewModel", "Cannot get data")
                }
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                Log.e("UserViewModel", "Cannot get data")
            }

        })
    }


}