package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentSendOtpBinding
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendOtpFragment : Fragment() {

    private lateinit var binding: FragmentSendOtpBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var userVm : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("keyUser", Context.MODE_PRIVATE)
        var getEmail = sharedPreferences.getString("name", "")
        binding.tvContentOtp2.text = "ke $getEmail"

        userVm.sendOtpRequest(getEmail!!)
        userVm.responseOtp.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
        }
    }


    fun inputOtp(){
        val inputOtp = binding.pinview.text.toString()
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        userVm.responseOtp.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
        }
        var getEmail = sharedPreferences.getString("email", "")
        userVm.sendOtpRequest(getEmail!!)

    }

}