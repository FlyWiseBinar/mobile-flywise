package com.binar.projekakhir.view.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentSendEmailOtpBinding
import com.binar.projekakhir.databinding.FragmentSendOtpBinding
import com.binar.projekakhir.viewmodel.UserViewModel


class SendEmailOtpFragment : Fragment() {
    private lateinit var binding: FragmentSendEmailOtpBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var userVm : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendEmailOtpBinding.inflate(inflater, container, false)
        return binding.root
    }


}