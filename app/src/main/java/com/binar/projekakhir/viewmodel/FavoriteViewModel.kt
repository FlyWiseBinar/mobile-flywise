package com.binar.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.favorite.Data
import com.binar.projekakhir.model.favorite.GetFavoriteResponse
import com.binar.projekakhir.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var api : ApiService) : ViewModel(){
    val _favorite : MutableLiveData<List<Data>> = MutableLiveData()

    val livedatafavorite : LiveData<List<Data>> = _favorite


    fun getfavorite(){
        api.getfavorite().enqueue(object : Callback<GetFavoriteResponse> {
            override fun onResponse(
                call: Call<GetFavoriteResponse>,
                response: Response<GetFavoriteResponse>
            ) {
                if (response.isSuccessful){
                    _favorite.value = response.body()!!.data
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

}