package com.binar.projekakhir.view.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentLoginBinding
import com.binar.projekakhir.model.auth.Data
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var userVm : UserViewModel
    private lateinit var binding: FragmentLoginBinding
    private var isSuccessLogin = false
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var data : Data


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()
        dontHaveAccount()
    }

    private fun dontHaveAccount() {
        binding.tvSignupHere.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }
    private fun login(){
        binding.btnSignin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            userValidation(email,password)

            Log.d("Login Fragment", isSuccessLogin.toString())
            if (isSuccessLogin){
                it.findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
            }else if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT)
                    .show()
            }else{
                binding.btnSignin.error = "Periksa kembali email dan password anda"
            }
        }
    }
    private fun forgetPassword(){
        binding.tvForgetPassword.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_resetPassFragment)
        }
    }

    private fun userValidation(email:String,pass:String) {
        userVm.responselogin.observe(requireActivity()) {
//            //when get data success, validate email and password to login
//            for (i in it.indices) {
//                //validate email and password using index data
//                val emailValidate = it[i]
//                val passValidate = it[i]
//
//                //create conditional to make sure email and password is available in response data
//                if (email == emailValidate.email && pass == passValidate.password) {
//                    Log.d("Login Fragment", "email validate: $emailValidate ")
//                    Log.d("Login Fragment", email)
//                    Log.d("Login Fragment", "pass validate: $passValidate ")
//                    Log.d("Login Fragment", pass)
//                    //get id
////                    val id = it[i].idUsers
//                    Log.d("Login Fragment", id.toString())
//                    userVm.saveIdPreferences("id",id!!.toString())
//
//
//                    isSuccesLogin = true
//                    break
//                } else if (email != emailValidate.email && pass == passValidate.password) {
//                    isSuccesLogin = false
//                } else if (email == emailValidate.email && pass != passValidate.password) {
//                    isSuccesLogin = false
//                } else {
//                    isSuccesLogin = false
//                }
//            }
                    }
    }


}