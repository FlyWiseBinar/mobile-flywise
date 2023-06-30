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
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentCheckBioPenumpangBinding
import com.binar.projekakhir.databinding.FragmentCheckoutBinding
import com.binar.projekakhir.databinding.FragmentCheckoutBioPemesananBinding
import com.binar.projekakhir.model.auth.resetpassword.UpdateProfilePost
import com.binar.projekakhir.viewmodel.CheckoutViewModel
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutBioPemesanFragment : Fragment() {

    private lateinit var binding : FragmentCheckoutBioPemesananBinding
    private lateinit var pref : SharedPreferences
    private lateinit var UserVm : UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBioPemesananBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        UserVm = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnSimpan.setOnClickListener {
           updatebiopemesan()

        }

        binding.switch1.setOnCheckedChangeListener { p0, isChecked ->

            if (isChecked) {
                binding.tvNamaKeluarga.visibility = View.VISIBLE
                binding.etNamaKeluarga.visibility = View.VISIBLE

            } else {
                binding.tvNamaKeluarga.visibility = View.GONE
                binding.etNamaKeluarga.visibility = View.GONE

            }
        }




    }

    private fun updatebiopemesan(){
        val token = pref.getString("token", "").toString()
        val inputnama = binding.txtInputLayoutNama.text.toString()
        val inputkeluarga = binding.txtInputLayoutNamaKeluarga.text.toString()
        val inputtelepon = binding.txtInputLayoutNomorTelp.text.toString()
        val inputemail = binding.txtInputLayoutEmail.text.toString()
        val dataUser = UpdateProfilePost(inputnama,inputtelepon, inputemail)
        UserVm.updateprofile(token, dataUser)

        navigationBundlingSf(dataUser)
        UserVm.getlivedataupdateprofile().observe(viewLifecycleOwner) {

            if (it.status) {
                findNavController().navigate(R.id.action_checkoutBioPemesanFragment_to_checkBioPenumpangFragment)
                Toast.makeText(context, "Berhasil Isi Data Pemesan", Toast.LENGTH_SHORT).show()
            }


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