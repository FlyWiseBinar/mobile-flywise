package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentLoginBinding
import com.binar.projekakhir.model.auth.Data
import com.binar.projekakhir.model.auth.login.LoginBody
import com.binar.projekakhir.model.auth.login.LoginResponse
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var userVm : UserViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var pref: SharedPreferences
    lateinit var listuserlogin: List<Data>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        binding.tvSignupHere.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.tvForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPassFragment)
        }

        binding.btnCrash.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        binding.btnSignin.setOnClickListener {

            if (binding.etEmail.text.toString().isEmpty()) {
                binding.etEmail.setError("Isi Username")
            } else if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.setError("Isi Password")
            } else if (binding.etPassword.text.toString().length < 6) {
                Toast.makeText(context, "Password kurang dari 6 karakter", Toast.LENGTH_SHORT).show()
           }
            else {
                login()


            }


        }

        userVm.setDataMessage()

            userVm.toastMessage.observe(viewLifecycleOwner){
            if(it != null){
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun login(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            userVm.responselogin.observe(viewLifecycleOwner, Observer {
//            listuserlogin = it
//            loginAuth(listuserlogin)
                if(it.status == true){
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
                        Toast.makeText(context, "User Berhasil Login", Toast.LENGTH_SHORT).show()


                }else{
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                }
                val sharedPref = pref.edit()
                sharedPref.putString("token", it.accessToken)
                sharedPref.apply()

            })
            userVm.postlogin(LoginBody(email, password))

        } else{
            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
        }
//        userVm.responselogin.observe(viewLifecycleOwner, Observer {
////            listuserlogin = it
////            loginAuth(listuserlogin)
//            if(it.status == true){
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
//                Toast.makeText(context,"User Berhasil Login", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
//            }
//            val sharedPref = pref.edit()
//            sharedPref.putString("token", it.accessToken)
//            sharedPref.apply()
//
//        })
//        userVm.postlogin(LoginBody(email, password))


    }




}