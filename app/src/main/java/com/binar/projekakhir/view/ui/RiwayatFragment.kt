package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentRiwayatBinding
import com.binar.projekakhir.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class RiwayatFragment : Fragment() {
    private lateinit var binding : FragmentRiwayatBinding
    private lateinit var pref : SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRiwayatBinding.inflate(inflater,container,false)
        return binding.root



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        login()


    }

    private fun login() {
        binding.btnMasukRiwayat.setOnClickListener {
            view?.post {
                findNavController().navigate(R.id.action_riwayatFragment2_to_loginFragment)
            }
        }
    }

    private fun isLogin() {
        Log.d("Berhasil", pref.getString("token", " ").toString())
        if (pref.getString("token", "").toString().isNotEmpty()) {
            binding.layoutLogin.visibility = View.VISIBLE
            Log.d("Berhasil Login", "berhasil")
            binding.layoutNonLogin.visibility = View.GONE

        } else {
            binding.layoutNonLogin.visibility = View.VISIBLE
            binding.layoutLogin.visibility = View.GONE
        }
    }



    override fun onResume() {
        super.onResume()
        isLogin()
    }





}