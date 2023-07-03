package com.binar.projekakhir.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.filterprice.GetFilterPriceResponse
import com.binar.projekakhir.model.searchairport.GetSearchAirportResponse
import com.binar.projekakhir.model.searchairport.data
import com.binar.projekakhir.model.searchtiket.Data
import com.binar.projekakhir.model.searchtiket.GetSearchTicketResponse
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class HomeViewModel @Inject constructor(var api:ApiService,
                                        private val sharedPreferences: SharedPreferences
                                        ) : ViewModel(){


    val _searchallticket : MutableLiveData<List<Data>> = MutableLiveData()
    val livedatasearchallticket : LiveData<List<Data>> = _searchallticket

    fun searchallticket(originAirport:String,destinationAirport:String,departureDate:String,arrivedDate:String){
        api.getallticket(originAirport, destinationAirport, departureDate, arrivedDate).enqueue(object : Callback<GetSearchTicketResponse>{
            override fun onResponse(
                call: Call<GetSearchTicketResponse>,
                response: Response<GetSearchTicketResponse>
            ) {
                if (response.isSuccessful){
                    _searchallticket.value = response.body()!!.data
                }
                else{
                    Log.e("HomeViewModel", "Cannot send data")
                }
            }

            override fun onFailure(call: Call<GetSearchTicketResponse>, t: Throwable) {
                Log.e("HomeViewModel", "Cannot send data1")
            }

        })
    }

    val _searchairport : MutableLiveData<List<data>> = MutableLiveData()
    val livedatasearchairport : LiveData<List<data>> = _searchairport
    fun searchairport(search : String){
        api.getairport(search).enqueue(object : Callback<GetSearchAirportResponse>{
            override fun onResponse(
                call: Call<GetSearchAirportResponse>,
                response: Response<GetSearchAirportResponse>
            ) {
                if (response.isSuccessful){
                    _searchairport.value = response.body()!!.data
                }
                else{
                    Log.e("HomeViewModel", "Cannot send data")
                }
            }

            override fun onFailure(call: Call<GetSearchAirportResponse>, t: Throwable) {
                Log.e("HomeViewModel", "Cannot send data")
            }

        })

    }

    fun cityfrom(originAirport:String){
        api.getdestinationsfrom(originAirport).enqueue(object :Callback<GetSearchTicketResponse>{
            override fun onResponse(
                call: Call<GetSearchTicketResponse>,
                response: Response<GetSearchTicketResponse>
            ) {
                if (response.isSuccessful){
                    _searchallticket.value = response.body()!!.data
                }
                else{
                    Log.e("HomeViewModel", "Can't Send Data ")
                }
            }

            override fun onFailure(call: Call<GetSearchTicketResponse>, t: Throwable) {
                Log.e("HomeViewModel", "Can't Send Data city from")
            }

        })

    }

    fun cityTo(destinationAirport:String){
        api.getdestinationsto( destinationAirport).enqueue(object :Callback<GetSearchTicketResponse>{
            override fun onResponse(
                call: Call<GetSearchTicketResponse>,
                response: Response<GetSearchTicketResponse>
            ) {
                if (response.isSuccessful){
                    _searchallticket.value = response.body()!!.data
                }
                else{
                    Log.e("HomeViewModel", "Can't Send Data ")
                }
            }

            override fun onFailure(call: Call<GetSearchTicketResponse>, t: Throwable) {
                Log.e("HomeViewModel", "Can't Send Data city from")
            }

        })
    }

    val filterprice : MutableLiveData<List<com.binar.projekakhir.model.filterprice.Data>> = MutableLiveData()
    val livedatafilterprice : LiveData<List<com.binar.projekakhir.model.filterprice.Data>> = filterprice

    fun getfilterprice(originAirport:String,destinationAirport:String,departureDate:String,arrivedDate:String, order: String){
        api.getfilterprice(originAirport, destinationAirport, departureDate, arrivedDate, order).enqueue(object :Callback<GetFilterPriceResponse>{
            override fun onResponse(
                call: Call<GetFilterPriceResponse>,
                response: Response<GetFilterPriceResponse>
            ) {
                if (response.isSuccessful){
                    filterprice.value = response.body()!!.data
                }
                else{
                    Log.e("HomeViewModel", "Cannot send data 3")
                }
            }

            override fun onFailure(call: Call<GetFilterPriceResponse>, t: Throwable) {
                Log.e("HomeViewModel", "Cannot send data 4")
            }

        })

    }

    fun savePenumpangPreferences(dewasa: Int,anak: Int,bayi: Int){
        val editor = sharedPreferences.edit()
        editor.putInt("dewasa",dewasa)
        editor.putInt("anak",anak)
        editor.putInt("bayi",bayi)
        editor.apply()
    }

    fun saveDatePref(date:String){
        val editor = sharedPreferences.edit()
        editor.putString("date", date)
        editor.apply()
    }

    fun saveKelasPreferences(nama:String,harga:Int,isSelected:Boolean){
        val editor = sharedPreferences.edit()
        editor.putString("kelas",nama)
        editor.putInt("harga",harga)
        editor.putBoolean("isSelected",isSelected)
        editor.apply()
    }

    fun saveDepartureDate(date: String){
        val editor = sharedPreferences.edit()
        editor.putString("departure",date)
        editor.apply()
    }

    fun saveCityFrom(city:String){
        val editor = sharedPreferences.edit()
        editor.putString("keyFrom",city)
        editor.apply()
    }

    fun saveCityTo(city:String){
        val editor = sharedPreferences.edit()
        editor.putString("keyTo",city)
        editor.apply()
    }

    fun saveselected(isSelected: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean("selected", isSelected)
        editor.apply()
    }

    fun saveIdDeparture(idDep:Int){
        val editor = sharedPreferences.edit()
        editor.putInt("idDep",idDep)
        editor.apply()
    }

    fun saveIdReturn(idReturn:Int){
        val editor = sharedPreferences.edit()
        editor.putInt("idReturn",idReturn)
        editor.apply()
    }

    fun saveIdTicket(idTicket:Int){
        val editor =  sharedPreferences.edit()
        editor.putInt("idTicket",idTicket)
        editor.apply()
    }

    fun savenama(savenama : String){
        val editor = sharedPreferences.edit()
        editor.putString("name", savenama)
        editor.apply()
    }

    fun getPenumpangDewasa():Int{
        return sharedPreferences.getInt("dewasa",1)
    }

    fun getPenumpangAnak():Int{
        return sharedPreferences.getInt("anak",0)
    }

    fun getPenumpangBayi():Int{
        return sharedPreferences.getInt("bayi",0)
    }

    fun getCityFrom():String?{
        return sharedPreferences.getString("keyFrom"," ")
    }

    fun getIdDep():Int?{
        return sharedPreferences.getInt("idDep",0)
    }

    fun getIdReturn(): Int?{
        return sharedPreferences.getInt("idReturn",0)
    }

    fun getnama() : String?{
        return sharedPreferences.getString("name", "")
    }



    fun getCityTo():String?{
        return sharedPreferences.getString("keyTo"," ")
    }

    fun getorder():String?{
        return sharedPreferences.getString("order","price")
    }

    fun getIdTicket():Int?{
        return sharedPreferences.getInt("idTicket",0)
    }


    fun getArrivedDate(): String? {
        val nameMonth = ArrayList<String>()
        nameMonth.add("Januari")
        nameMonth.add("Februari")
        nameMonth.add("Maret")
        nameMonth.add("April")
        nameMonth.add("Mei")
        nameMonth.add("Juni")
        nameMonth.add("Juli")
        nameMonth.add("Agustus")
        nameMonth.add("September")
        nameMonth.add("Oktober")
        nameMonth.add("November")
        nameMonth.add("Desember")

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val bulan = nameMonth[month]
//        val tanggal = "2023-5-27"
        val defaultTanggal = "$year-${month+1}-$day"
        return sharedPreferences.getString("date",defaultTanggal)
    }

    fun getDepartureDate():String?{
        val nameMonth = ArrayList<String>()
        nameMonth.add("Januari")
        nameMonth.add("Februari")
        nameMonth.add("Maret")
        nameMonth.add("April")
        nameMonth.add("Mei")
        nameMonth.add("Juni")
        nameMonth.add("Juli")
        nameMonth.add("Agustus")
        nameMonth.add("September")
        nameMonth.add("Oktober")
        nameMonth.add("November")
        nameMonth.add("Desember")

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val bulan = nameMonth[month]
//        val tanggal = "2023-5-26"
        val defaultTanggal = "$year-${month+1}-$day"
        return sharedPreferences.getString("departure",defaultTanggal)
    }

    fun getNamaKelas():String?{
        return sharedPreferences.getString("kelas","Economy")
    }

    fun getHargaKelas():Int{
        return sharedPreferences.getInt("harga",0)
    }

    fun getSelected():Boolean{
        return sharedPreferences.getBoolean("isSelected",false)
    }

    fun getCheckedSwitch() : Boolean{
        return  sharedPreferences.getBoolean("selected", false)
    }


    fun deletePref(){
        val editor = sharedPreferences.edit()
        editor.remove("dewasa")
        editor.remove("anak")
        editor.remove("bayi")
        editor.remove("date")
        editor.remove("kelas")
        editor.remove("harga")
        editor.remove("isSelected")
        editor.remove("departure")
        editor.apply()
    }




    companion object{
        private const val TAG = "DestinasiViewModel"
    }


    private val _search = MutableLiveData<List<data>>()
    val search: LiveData<List<data>> = _search

    fun callGetSearchAirport(city: String) {
        api.getSearchAirport(city).enqueue(object : Callback<GetSearchAirportResponse> {
            override fun onResponse(
                call: Call<GetSearchAirportResponse>,
                response: Response<GetSearchAirportResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _search.postValue(data.data as List<data>?)
                    }
                } else {
                    Log.e("Error : ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetSearchAirportResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }
}
