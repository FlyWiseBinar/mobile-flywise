package com.binar.projekakhir.network

import com.binar.projekakhir.model.auth.*
import com.binar.projekakhir.model.auth.resetpassword.ResetPassPost
import retrofit2.Call
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
    ): Call<ResponseRegister>

    @POST("auth/login")
    fun login(@Body loginBody: LoginBody):Call<List<Data>>

    @POST("auth/reset-password/send-otp")
    fun resetsendotp(@Body sendOtpResponse: SendOtpResponse):Call<List<Data>>

    @PUT("auth/reset-password/reset")
    fun putresetpassword(
        @Body request: ResetPassPost
    ) : Call<List<Data>>

    @PUT("auth/profile")
    fun putupdateprofile(
        @Body request : ResetPassPost
    ) : Call<List<Data>>



}