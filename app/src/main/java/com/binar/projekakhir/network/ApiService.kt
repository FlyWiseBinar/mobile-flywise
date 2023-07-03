package com.binar.projekakhir.network

import com.binar.projekakhir.model.auth.*
import com.binar.projekakhir.model.auth.login.LoginBody
import com.binar.projekakhir.model.auth.otp.SendOtpResponse
import com.binar.projekakhir.model.auth.otp.VerifyOtpResponse
import com.binar.projekakhir.model.auth.resetpassword.ResetPassPost
import com.binar.projekakhir.model.auth.resetpassword.UpdateProfilePost
import com.binar.projekakhir.model.checkout.PenumpangRoundTripRequest
import com.binar.projekakhir.model.checkout.PostCheckoutResponse
import com.binar.projekakhir.model.detail.GetResponseFindSchedulebyId
import com.binar.projekakhir.model.favorite.GetFavoriteResponse
import com.binar.projekakhir.model.filterprice.GetFilterPriceResponse
import com.binar.projekakhir.model.history.GetHistoryResponse
import com.binar.projekakhir.model.paymentcreate.GetPaymentCreateResponse
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
    ) : Call<Data>


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

    @GET("schedule/{id}")
    fun detailticket(
        @Path("id") id:Int
    ):Call<GetResponseFindSchedulebyId>

    @GET("schedule/search")
    fun getfilterprice(
        @Query("originAirport") originAirport:String,
        @Query("destinationAirport") destinationAirport:String,
        @Query("departureDate") departureDate:String,
        @Query("arrivedDate") arrivedDate:String,
        @Query("order") order:String
    ):Call<GetFilterPriceResponse>

    @GET("schedule/search")
    fun getdestinationsfrom(
        @Query("originAirport") originAirport:String,
    ): Call<GetSearchTicketResponse>

    @GET("schedule/search")
    fun getdestinationsto(
        @Query("destinationAirport") destinationAirport:String,

    ): Call<GetSearchTicketResponse>


    @GET("schedule/airport?search=")
    fun getSearchAirport(
        @Query("city") city:String
    ) : Call<GetSearchAirportResponse>

   @POST("order/checkout")
   fun postcheckout(
       @Header("Authorization") token:String,
       @Body data : PenumpangRoundTripRequest
   ) : Call<PostCheckoutResponse>

   @GET("order/history")
   fun gethistory(
       @Header("Authorization") token:String
   ) : Call<GetHistoryResponse>

   @POST("order/payment")
   fun postpayment(
       @Header("Authorization") token:String,
       @Query("paymentCode") paymentCode : String,
       @Query("paymentTypeId") paymentTypeId : Int

   ) : Call<GetPaymentCreateResponse>

//    @GET("order/historysearch")
//    fun getdetailhistory(
//        @Header("Authorization") token:String,
//        @Query("orderCode") orderCode:String
//    ):Call<ResponseHistoryByCode>












}