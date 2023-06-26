package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentProfileBinding
import com.binar.projekakhir.model.auth.resetpassword.ResetPassPost
import com.binar.projekakhir.model.auth.resetpassword.UpdateProfilePost
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userVm : UserViewModel
    private lateinit var pref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)


        binding.btnLogout.setOnClickListener {
            pref.edit().clear().apply()
            Log.d("DataToken", pref.getString("token", "").toString())
            findNavController().navigate(R.id.action_profileFragment2_to_homeFragment2)

        }

        binding.btnUpdate.setOnClickListener {
            updateUserProfile()
        }

        getdataprofile()


    }

    fun updateUserProfile() {
//        val pass = pref.getString("password", "").toString()

        val token = pref.getString("token", "").toString()

        val inputnama = binding.txtFullname.text.toString()
        val inputtlp = binding.txtTelephone.text.toString()
        val inputemail = binding.txtEmail.text.toString()
        val dataUser = UpdateProfilePost(inputnama,inputtlp, inputemail)

        userVm.updateprofile(token, dataUser)
        navigationBundlingSf(dataUser)
        userVm.getlivedataupdateprofile().observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(context, "Update Profile Berhasil", Toast.LENGTH_SHORT)
            }
        }


    }

    fun getdataprofile(){
        val token = pref.getString("token", "").toString()
       userVm.userprofile(token)
        userVm.getProfile.observe(viewLifecycleOwner){
            Log.d("Profile","email : ${it.email}")
            binding.txtEmail.setText(it.email)
            binding.txtTelephone.setText(it.telephone)
            binding.txtFullname.setText(it.fullName)
        }

    }

    private fun navigationBundlingSf(currentUser: UpdateProfilePost) {
        //shared pref to save log in history
        val sharedPref =pref.edit()
        sharedPref.putString("email", currentUser.email)
        sharedPref.putString("telephone", currentUser.telephone)
        sharedPref.putString("fullname", currentUser.fullName)
        sharedPref.apply()
    }





}