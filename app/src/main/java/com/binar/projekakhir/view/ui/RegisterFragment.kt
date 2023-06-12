package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentRegisterBinding
import com.binar.projekakhir.model.auth.Data
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var userVm: UserViewModel

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

        register()


    }

    private fun register() {
        binding.btnDaftar.setOnClickListener {
            val nama = binding.txtInputLayoutNama.text.toString()
            val email = binding.txtInputLayoutEmail.text.toString()
            val telepon = binding.txtInputNoTelp.text.toString()
            val pass = binding.txtInputLayoutPass.text.toString()

            if (nama.isEmpty() || email.isEmpty() || telepon.isEmpty() || pass.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT)
                    .show()
            } else {

                userVm.register(registerData = Data("",email, nama,0, pass, true, telepon, "" ))
                Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)


            }
        }
    }
}
