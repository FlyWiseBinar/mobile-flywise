package com.binar.projekakhir.view.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentCheckoutBinding
import com.binar.projekakhir.databinding.FragmentPaymentBinding
import com.binar.projekakhir.viewmodel.DetailViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private lateinit var binding : FragmentPaymentBinding
    private val detailVm: DetailViewModel by viewModels()
    private lateinit var pref : SharedPreferences
    private val homeVm : HomeViewModel by viewModels()
//    private val navArgs : PaymentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val id = navArgs.idData?.toInt()

//        detailVm.getdetailticket(id!!)
//        detailVm._detail.observe(viewLifecycleOwner) {
//
//        }
    }


}