package com.binar.projekakhir.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.DialogFilterBinding
import com.binar.projekakhir.datauser.FilterDummy
import com.binar.projekakhir.datauser.KelasDummy
import com.binar.projekakhir.view.adapter.SetKelasAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterHarga : BottomSheetDialogFragment() {
    private lateinit var filterList:ArrayList<FilterDummy>
    private lateinit var binding: DialogFilterBinding
    //private lateinit var kelasAdapter: SetKelasAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_filter, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterList = ArrayList()
        filterList.add(FilterDummy("Paling awal","Termurah", "Paling awal"))
        filterList.add(FilterDummy("Paling akhir","Termahal", "Paling akhir"))
    }

    fun onClick(){
        //ntar dulu mas masih bingung :")
    }


    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
