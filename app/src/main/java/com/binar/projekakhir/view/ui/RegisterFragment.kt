package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentRegisterBinding
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    lateinit var sharedRegis: SharedPreferences
    private lateinit var userVm : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        sharedRegis = requireContext().getSharedPreferences("keyUser", Context.MODE_PRIVATE)


        binding.btnDaftar.setOnClickListener {
            //eksekusi callback
            register{
                sendOtpToEmail()
                //menampilkan email yang dikirim ke tv halaman otp
                var getEmail = binding.txtInputLayoutEmail.text.toString()
                var addUser = sharedRegis.edit()
                addUser.putString("name", getEmail)
                addUser.apply()
            }
        }

        binding.tvMasukdisini.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


    }

    // menggunakan pendekatan yang disebut sebagai callback atau chaining asynchronous operations.
    //memanggil fungsi register() dan meneruskan fungsi sendOtpToEmail() sebagai callback.
    // Callback sendOtpToEmail() tersebut akan dipanggil setelah operasi register() selesai
    private fun register(callback: () -> Unit) {
        val fullName = binding.txtInputLayoutNama.text.toString()
        val email = binding.txtInputLayoutEmail.text.toString()
        val telephone = "0" + binding.txtInputNoTelp.text.toString()
        val password = binding.txtInputLayoutPass.text.toString()

        if (fullName.isEmpty() || email.isEmpty() || telephone.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
//            userVm.postregist("ilham","ilham@gmail.com", "123456","0821233423121")
            userVm.postregist(fullName,email,password,telephone)
            userVm.responseRegister.observe(viewLifecycleOwner){
                if (it.status == true){
                    findNavController().navigate(R.id.action_registerFragment_to_sendOtpFragment)
                    // Panggil callback setelah register selesai
                    callback()
                }
            }
//            Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendOtpToEmail() {
        val email = binding.txtInputLayoutEmail.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
            userVm.sendOtpRequest(email)
            userVm.responseOtp.observe(viewLifecycleOwner){
                if (it.message == "Otp sent successfully"){

                }
            }
            Toast.makeText(requireContext(), "Kode OTP telah dikirim!", Toast.LENGTH_SHORT).show()
        }
    }

    }

