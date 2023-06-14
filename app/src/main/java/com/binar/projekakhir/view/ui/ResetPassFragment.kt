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
import com.binar.projekakhir.databinding.FragmentResetPassBinding
import com.binar.projekakhir.model.auth.resetpassword.ResetPassPost
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPassFragment : Fragment() {

    private lateinit var binding : FragmentResetPassBinding
    private lateinit var pref : SharedPreferences
    private lateinit var userVm :UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResetPassBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            updateUserProfile()
            activity?.onBackPressed()
        }
    }

    fun updateUserProfile() {
        pref= requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
//        val pass = pref.getString("password", "").toString()
        val email = pref.getString("email", "").toString()
        val confirm_pass = pref.getString("confirm_pass", "").toString()
        val inputemail = binding.txtInputLayoutEmail.text.toString()
        val inputpass = binding.inputPass.text.toString()
        val inputconfirmpass = binding.inputConfirmpass.text.toString()
        val dataUser = ResetPassPost(inputemail,inputpass,inputconfirmpass)
        userVm.resetpass(dataUser)
        navigationBundlingSf(dataUser)
        userVm.getliveresetpass().observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT)
            }
        }


    }

    private fun navigationBundlingSf(currentUser: ResetPassPost) {
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        //shared pref to save log in history
        val sharedPref =pref.edit()
        sharedPref.putString("email", currentUser.email)
        sharedPref.putString("password", currentUser.password)
        sharedPref.putString("confirm_pass", currentUser.confirm_password)
        sharedPref.apply()
    }



}