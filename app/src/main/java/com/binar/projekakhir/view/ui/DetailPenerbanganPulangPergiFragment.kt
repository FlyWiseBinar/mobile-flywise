package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentDetailPenerbanganPulangPergiBinding
import com.binar.projekakhir.util.Utill
import com.binar.projekakhir.view.adapter.SectionPagerAdapter
import com.binar.projekakhir.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPenerbanganPulangPergiFragment : Fragment() {

    private lateinit var binding : FragmentDetailPenerbanganPulangPergiBinding
    private val homeVm : HomeViewModel by viewModels()
    private lateinit var pref : SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailPenerbanganPulangPergiBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        val id = arguments?.getInt("id")
        val idDeparture = arguments?.getString("idDeparture")
        val idReturn = arguments?.getString("idReturn")
        val hargaPergi = arguments?.getInt("hargaPergi")
        val hargaPulang = arguments?.getInt("hargaPulang")

        Log.d("DetailPenerbanganPP", "Harga: $hargaPergi")
        Log.d("DetailPenerbanganPP", "Harga: $hargaPulang")



        val sectionPagerAdapter = SectionPagerAdapter(activity as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs,binding.viewPager){
                tab,position ->  tab.text = resources.getString(
            TAB_TITLES[position]
        )
        }.attach()

        val priceTotal = hargaPergi?.plus(hargaPulang!!)
        binding.txtHargaTotal.text = Utill.getPriceIdFormat(priceTotal!!)

        binding.btnPilih.setOnClickListener {
            homeVm.saveIdTicket(id!!)
            if (pref.getString("token", "").toString().isNotEmpty()) {
                if (findNavController().currentDestination!!.id == R.id.detailPenerbanganPulangPergiFragment) {

                    findNavController().navigate(R.id.action_detailPenerbanganPulangPergiFragment_to_checkoutBioPemesanFragment)

                }

            } else {
                val fragId = findNavController().currentDestination?.id
                findNavController().popBackStack(fragId!!, true)
                findNavController().navigate(R.id.bottomSheetCekUserLogin)
            }
        }
    }
    companion object{
        private val TAB_TITLES = intArrayOf(
            R.string.str_pergi,
            R.string.str_pulang
        )
    }




    }


