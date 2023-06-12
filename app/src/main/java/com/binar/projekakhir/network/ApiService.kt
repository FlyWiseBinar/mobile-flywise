package com.binar.projekakhir.network

import com.binar.projekakhir.model.auth.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    fun register(@Body registerData: Data): Call<List<RegisterBody>>

    @POST("auth/login")
    fun login(@Body loginData: Data):Call<List<LoginBody>>
}