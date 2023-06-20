package com.binar.projekakhir.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.projekakhir.model.favorite.GetFavoriteResponse
import com.binar.projekakhir.model.favorite.Schedule
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    val _favorite : MutableLiveData<List<Schedule>> = MutableLiveData()

    val livedatafavorite : LiveData<List<Schedule>> = _favorite


    fun getfavorite(){
        api.getfavorite().enqueue(object : Callback<GetFavoriteResponse> {
            override fun onResponse(
                call: Call<GetFavoriteResponse>,
                response: Response<GetFavoriteResponse>
            ) {
                if (response.isSuccessful){
                    _favorite.value = response.body()!!.schedule
                }
                else{
                    Log.e("UserViewModel", "Cannot send data")
                }
            }

            override fun onFailure(call: Call<GetFavoriteResponse>, t: Throwable) {
                Log.e("UserViewModel", "Cannot send data 1")
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
        editor.putString("from",city)
        editor.apply()
    }

    fun saveCityTo(city:String){
        val editor = sharedPreferences.edit()
        editor.putString("to",city)
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
        return sharedPreferences.getString("from","Jakarta")
    }

    fun getCityTo():String?{
        return sharedPreferences.getString("to","Jakarta")
    }

    fun getDatePref(): String? {
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
        val defaultTanggal = "$year-$month-$day"
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
        val defaultTanggal = "$year-$month-$day"
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
}