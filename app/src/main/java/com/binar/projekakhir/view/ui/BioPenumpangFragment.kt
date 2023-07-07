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
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentBioPenumpangBinding
import com.binar.projekakhir.model.checkout.Passenger
import com.binar.projekakhir.viewmodel.CheckoutViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BioPenumpangFragment : Fragment() {

    private lateinit var binding : FragmentBioPenumpangBinding
    private lateinit var pref : SharedPreferences
    private val CheckoutVm: CheckoutViewModel by viewModels()
    private val HomeVm: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBioPenumpangBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        binding.btnLanjutKursi.setOnClickListener {
            getInputPassenger()
        }

    }

    fun getInputPassenger() {
        val idTicket = HomeVm.getTicketId()
        val id = arguments?.getInt("id")
        val token = pref.getString("token", "").toString()
        val name = binding.txtInputLayoutNama.text.toString()
        val bornDate = binding.txtInputLayoutBirthDay.text.toString()
        val citizen = binding.txtInputLayoutKewarga.text.toString()
        val idNumber = binding.txtInputKtpPaspor.text.toString()
        val country = binding.txtInputNegPenerb.text.toString()

        if (name.isEmpty()) {
            binding.txtInputLayoutNama.error = "Name Still Empty"
            binding.txtInputLayoutNama.requestFocus()
            return
        } else if (bornDate.isEmpty()) {
            binding.txtInputLayoutBirthDay.error = "Name Still Empty"
            binding.txtInputLayoutBirthDay.requestFocus()
            return
        } else if (citizen.isEmpty()) {
            binding.txtInputLayoutKewarga.error = "Name Still Empty"
            binding.txtInputLayoutKewarga.requestFocus()
            return
        } else if (idNumber.isEmpty()) {
            binding.txtInputKtpPaspor.error = "Name Still Empty"
            binding.txtInputKtpPaspor.requestFocus()
            return
        } else if (country.isEmpty()) {
            binding.txtInputNegPenerb.error = "Name Still Empty"
            binding.txtInputNegPenerb.requestFocus()
            return
        } else {
            val datapenumpang = Passenger("", bornDate, "", "", idTicket!!, country,idNumber,name,citizen,idTicket, "", "")
//            CheckoutVm.postbiopemesan(token,datapenumpang)
            CheckoutVm.livebiopemesanan.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(context, "Success Add Passenger", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_bioPenumpangFragment_to_checkoutFragment)
                }else{
                    Toast.makeText(context, "Fail Add Passenger", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}