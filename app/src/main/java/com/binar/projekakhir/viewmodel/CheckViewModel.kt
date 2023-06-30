package com.binar.projekakhir.viewmodel

import androidx.lifecycle.ViewModel
import com.binar.projekakhir.model.checkout.*
import com.binar.projekakhir.model.checkoutrequest.GetCheckoutRequest

class CheckViewModel:ViewModel() {
    private val dataList: MutableList<PenumpangPost> = mutableListOf()

    fun addData(data:PenumpangPost) {
        dataList.add(data)
    }

    fun getDataList(): List<PenumpangPost> {
        return dataList
    }

//    private val datalistschedule: MutableList<Schedule> = mutableListOf()
//
//    fun addData(dataschedule:Schedule) {
//        datalistschedule.add(dataschedule)
//    }
//
//    fun getDataListSchedule(): List<Schedule> {
//        return datalistschedule
//    }

}