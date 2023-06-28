package com.binar.projekakhir.viewmodel

import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.checkout.Data
import com.binar.projekakhir.model.checkout.Passenger
import com.binar.projekakhir.model.checkout.PenumpangPost

class CheckViewModel:ViewModel() {
    private val dataList: MutableList<PenumpangPost> = mutableListOf()

    fun addData(data:PenumpangPost) {
        dataList.add(data)
    }

    fun getDataList(): List<PenumpangPost> {
        return dataList
    }

}