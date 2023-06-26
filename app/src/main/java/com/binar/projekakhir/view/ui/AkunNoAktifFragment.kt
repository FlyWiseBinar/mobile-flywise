package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentAkunNoAktifBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AkunNoAktifFragment : Fragment() {

    private lateinit var binding: FragmentAkunNoAktifBinding
    private lateinit var pref: SharedPreferences
    private lateinit var idUser: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAkunNoAktifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        super.onViewCreated(view, savedInstanceState)
        idUser = pref.getString("id", "").toString()

        login()

        binding.layoutUbahProfil.setOnClickListener {
            findNavController().navigate(R.id.action_akunNoAktifFragment_to_profileFragment2)
        }


    }

    private fun login() {
        binding.btnLogin.setOnClickListener {
            view?.post {
                findNavController().navigate(R.id.action_akunNoAktifFragment_to_loginFragment)
            }
        }

        binding.layoutUbahProfil.setOnClickListener {
            findNavController().navigate(R.id.action_akunNoAktifFragment_to_profileFragment2)
        }

        binding.layoutKeluar.setOnClickListener {
            pref.edit().clear().apply()
            Log.d("DataToken", pref.getString("token", "").toString())
            findNavController().navigate(R.id.action_akunNoAktifFragment_to_homeFragment2)
        }




//    private fun stateShowLogin(it: Boolean) {
//        if (it) {
//            binding.layoutAkun.visibility = View.VISIBLE
//            binding.layoutNoLogin.visibility = View.GONE
//        } else {
//            binding.layoutAkun.visibility = View.GONE
//            binding.layoutNoLogin.visibility = View.VISIBLE
//        }
//    }


    }

    private fun isLogin() {
        Log.d("Berhasil", pref.getString("token", " ").toString())
        if (pref.getString("token", "").toString().isNotEmpty()) {
            binding.akunlogin.visibility = View.VISIBLE
            Log.d("Berhasil Login", "berhasil")
            binding.layoutNoLogin.visibility = View.GONE

        } else {
            binding.layoutNoLogin.visibility = View.VISIBLE
            binding.akunlogin.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }
}

