package com.binar.projekakhir.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentSetPenumpangBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetPenumpangFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentSetPenumpangBinding

    var tiketDewasa= 0
    var tiketAnak = 0
    var tiketBayi = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSetPenumpangBinding.inflate(layoutInflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClose()

        val dewasa = setDewasa(tiketDewasa)
        val bayi = setBayi(tiketBayi)
        val anak = setAnak(tiketAnak)


        binding.tvPassangerBaby.text = bayi.toString()
        binding.tvPassangerChild.text = dewasa.toString()
        binding.tvPassangerAdult.text = anak.toString()

        binding.btnSaveSeatPassenger.setOnClickListener {
            val tvDewasa = binding.tvPassangerAdult.text.toString()
            val tvBayi = binding.tvPassangerBaby.text.toString()
            val tvAnak = binding.tvPassangerChild.text.toString()

            val total = tvDewasa.toInt() + tvBayi.toInt() + tvAnak.toInt()
            val bundle = Bundle()
            bundle.putInt("dewasa", total)
            findNavController().navigate(R.id.action_setPenumpangFragment_to_homeFragment2,bundle)

        }
    }

    private fun setClose() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setBayi(tiketBayi:Int):Int {
        binding.tvPassangerBaby.text = tiketBayi.toString()
        var tiketBayi1 = tiketBayi

        binding.addPassangerBaby.setOnClickListener {
            tiketBayi1 += 1
            binding.tvPassangerBaby.text = tiketBayi1.toString()
        }

        binding.decPassangerBaby.setOnClickListener {
            if (tiketBayi1 > 0) {
                tiketBayi1 -= 1
                binding.tvPassangerBaby.text = tiketBayi1.toString()
            }
        }
        return tiketBayi1
    }

    private fun setAnak(tiketAnak:Int):Int{
        binding.tvPassangerChild.text = tiketAnak.toString()
        var tiketAnak1 = tiketAnak
        binding.addPassangerChild.setOnClickListener {
            tiketAnak1 += 1
            binding.tvPassangerChild.text = tiketAnak1.toString()
        }

        binding.decPassangerChild.setOnClickListener {
            if (tiketAnak1 > 0) {
                tiketAnak1 -= 1
                binding.tvPassangerChild.text = tiketAnak1.toString()
            }
        }
        return tiketAnak1
    }

    private fun setDewasa(tiketDewasa:Int):Int{
        binding.tvPassangerAdult.text = tiketDewasa.toString()

        var tiketDewasa1 = tiketDewasa
        binding.addPassangerAdult.setOnClickListener {
            tiketDewasa1 += 1
            binding.tvPassangerAdult.text = tiketDewasa1.toString()

        }

        binding.decPassangerAdult.setOnClickListener {
            if (tiketDewasa1 > 0) {
                tiketDewasa1 -= 1
                binding.tvPassangerAdult.text = tiketDewasa1.toString()
            }
        }
        return tiketDewasa1
    }



}