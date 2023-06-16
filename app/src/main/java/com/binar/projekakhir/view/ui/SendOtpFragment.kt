package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentSendOtpBinding
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendOtpFragment : Fragment() {

    private lateinit var binding: FragmentSendOtpBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var userVm : UserViewModel
    private lateinit var countDownTimer: CountDownTimer
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
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)

        sharedPreferences = requireContext().getSharedPreferences("keyUser", Context.MODE_PRIVATE)
        var getEmail = sharedPreferences.getString("name", "")
        binding.tvContentOtp2.text = "ke $getEmail"
        startTimer()

        binding.btnVerif.setOnClickListener{
            verifyOtp()
        }



    }


private fun verifyOtp() {
    val email = sharedPreferences.getString("name", "")
    val otp = binding.pinview.text.toString()

    if (otp.isEmpty()) {
        Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
    } else {
        userVm.verifyOtpRequest(email!!, otp)
        userVm.verifyOtp.observe(viewLifecycleOwner){
            if (it.message == "OTP verified successfully"){
                findNavController().navigate(R.id.action_sendOtpFragment_to_loginFragment)
                Toast.makeText(requireContext(), "Verifikasi Berhasil", Toast.LENGTH_SHORT).show()
            } else if (it.message == "Invalid OTP") {
                Toast.makeText(requireContext(), "Maaf, Kode OTP Salah!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Terjadi kesalahan saat verifikasi OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


    private fun startTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                val timerText = "Kirim Ulang OTP dalam $secondsRemaining detik"
                binding.tvContentOtp3.text = timerText
            }

            // Implementasikan logika ketika time is up
            override fun onFinish() {
                binding.tvContentOtp3.text = "Kirim Ulang"
                // Change style for tvContentOtp3
                binding.tvContentOtp3.setTypeface(null, Typeface.BOLD)
                binding.tvContentOtp3.setTextColor(Color.RED)

                binding.tvContentOtp3.isClickable = true

                binding.tvContentOtp3.setOnClickListener{
                    resetTimer()
                    reSendOtpToEmail()
                }

            }
        }
        countDownTimer.start()
    }
    private fun resetTimer() {
        binding.tvContentOtp3.isClickable = false
        binding.tvContentOtp3.text = "Kirim Ulang OTP dalam 60 detik"

        // Reset style for tvContentOtp3
        binding.tvContentOtp3.setTypeface(null, Typeface.NORMAL)
        binding.tvContentOtp3.setTextColor(Color.BLACK)

        countDownTimer.cancel()
        startTimer()
    }
    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }

    private fun reSendOtpToEmail() {
        val email = sharedPreferences.getString("name", "")

        if (email!!.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
            userVm.resendOtpRequest(email!!)
            userVm.resendOtp.observe(viewLifecycleOwner){
                if (it.message == "Otp sent successfully"){
                //Toast.makeText(requireContext(), "Kode OTP telah dikirim!", Toast.LENGTH_SHORT).show()
                }
            }
            Toast.makeText(requireContext(), "Kode OTP telah dikirim!", Toast.LENGTH_SHORT).show()

        }
    }

}

