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

        binding.btnSignin.setOnClickListener {

            if (binding.etEmail.text.toString().isEmpty()) {
                binding.etEmail.setError("Isi Username")
            } else if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.setError("Isi Password")
            } else {
                login()


            }


        }


    }

    private fun login(){

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        userVm.responselogin.observe(viewLifecycleOwner, Observer {
//            listuserlogin = it
//            loginAuth(listuserlogin)
            if(it.status == true){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
            }
            val sharedPref = pref.edit()
            sharedPref.putString("token", it.accessToken)
            sharedPref.apply()

        })
        userVm.postlogin(LoginBody(email, password))


    }

    private fun loginAuth(userDataList: LoginResponse) {
        //make shared preference that saving log in activity history
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)

        //get all data from user input
        val inputEmail = binding.etEmail.text.toString()
        val inputPassword = binding.etPassword.text.toString()



        //checking email and password of user to authenticate
//        for (i in userDataList.indices) {
//            if (inputPassword == userDataList[i].password && inputEmail == userDataList[i].email) {
//                Toast.makeText(requireContext(), "Berhasil login", Toast.LENGTH_SHORT).show()
//                //bundling all information about user
//                navigationBundlingSf(userDataList[i])
//                break
//            } else if (i == userDataList.lastIndex && inputPassword != userDataList[i].password && inputEmail != userDataList[i].email) {
//                binding.etPassword.error = "Password Tidak Sesuai"
//                binding.etEmail.error = "Email Tidak Sesuai"
//            }
//        }
    }

//    private fun navigationBundlingSf(currentUser: Data) {
//        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
//        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
//        //shared pref to save log in history
//        val sharedPref = pref.edit()
//        sharedPref.putString("email", currentUser.email)
//        sharedPref.putString("password", currentUser.password)
//        sharedPref.putString("token", currentUser.)
//        userVm.responselogin.observe(viewLifecycleOwner){
//            it.
//        }
//        sharedPref.putString("status", currentUser.status.toString())
//        sharedPref.apply()
//        Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_checkoutBioPemesanFragment)
//
//
//
//    }


}