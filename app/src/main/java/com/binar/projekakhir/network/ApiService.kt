package com.binar.projekakhir.network

import com.binar.projekakhir.model.auth.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
//    @POST("auth/register")
//    fun register(@Body registerData: Data): Call<List<RegisterBody>>
//
//    @POST("auth/login")
//    fun login(@Body loginData: Data):Call<List<LoginBody>>

    @FormUrlEncoded
    @POST("auth/register")
    fun register(
        @Field("fullName") fullName: String,
        @Field("email") email :String,
        @Field("password") password:String,
        @Field("telephone") telephone:String,
    ): Call<Data>

    @POST("auth/login")
    fun login(@Body loginBody: LoginBody):Call<List<Data>>

}