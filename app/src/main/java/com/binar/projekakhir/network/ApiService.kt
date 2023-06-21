package com.binar.projekakhir.network

import com.binar.projekakhir.model.auth.*
import com.binar.projekakhir.model.auth.login.LoginBody
import com.binar.projekakhir.model.auth.otp.SendOtpResponse
import com.binar.projekakhir.model.auth.otp.VerifyOtpResponse
import com.binar.projekakhir.model.auth.resetpassword.ResetPassPost
import com.binar.projekakhir.model.auth.resetpassword.UpdateProfilePost
import com.binar.projekakhir.model.favorite.GetFavoriteResponse
import com.binar.projekakhir.model.searchairport.GetSearchAirportResponse
import com.binar.projekakhir.model.searchtiket.GetSearchTicketResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("auth/register")
    fun register(
        @Field("fullName") fullName: String,
        @Field("email") email :String,
        @Field("password") password:String,
        @Field("telephone") telephone:String,
    ): Call<ResponseRegister>

    @POST("auth/login")
    fun login(@Body loginBody: LoginBody):Call<LoginResponse>

    @POST("auth/reset-password/send-otp")
    fun resetsendotp(@Body sendOtpResponse: SendOtpResponse):Call<List<Data>>

    @PUT("auth/reset-password/reset")
    fun putresetpassword(
        @Body request: ResetPassPost
    ) : Call<List<Data>>

    @PUT("auth/profile")
    fun putupdateprofile(
        @Header("Authorization") token:String,
        @Body request : UpdateProfilePost
    ) : Call<List<Data>>


    //sendotp
    @FormUrlEncoded
    @POST("auth/send-otp")
    fun sendOtp(
        @Field("email") email: String,
    ): Call<SendOtpResponse>

    //verify otp
    @FormUrlEncoded
    @POST("auth/verify-otp")
    fun verifyOtp(
        @Field("email") email: String,
        @Field("otp") otp: String,
    ): Call<VerifyOtpResponse>


    //resend otp
    @FormUrlEncoded
    @POST("auth/resend-otp")
    fun resendOtp(
        @Field("email") email: String,
    ): Call<SendOtpResponse>

    @GET("auth/whoami")
    fun getprofile(
        @Header("Authorization") token:String,
    ) : Call<GetUserResponse>


    @GET("schedule/favorite")
    fun getfavorite() : Call<GetFavoriteResponse>

    @GET("schedule/search")
    fun getallticket(
        @Query("originAirport") originAirport:String,
        @Query("destinationAirport") destinationAirport:String,
        @Query("departureDate") departureDate:String,
        @Query("arrivedDate") arrivedDate:String
    ) : Call<GetSearchTicketResponse>

    @GET("schedule/search")
    fun getairport(
        @Query("search") search : String
    ) : Call<GetSearchAirportResponse>






}