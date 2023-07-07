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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentCheckBioPenumpangBinding
import com.binar.projekakhir.model.auth.resetpassword.UpdateProfilePost
import com.binar.projekakhir.model.checkout.*
import com.binar.projekakhir.model.checkoutrequest.GetCheckoutRequest
import com.binar.projekakhir.view.adapter.PenumpangAdapter
import com.binar.projekakhir.viewmodel.CheckViewModel
import com.binar.projekakhir.viewmodel.CheckoutViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import com.binar.projekakhir.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckBioPenumpangFragment : Fragment() {

    private lateinit var binding: FragmentCheckBioPenumpangBinding
    private val HomeVm: HomeViewModel by viewModels()
    private val CheckoutVm: CheckoutViewModel by viewModels()
    private lateinit var pref: SharedPreferences
    private lateinit var UserVm: UserViewModel
    private  val CheckVm: CheckViewModel by activityViewModels()
    private lateinit var penumpangAdapter: PenumpangAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckBioPenumpangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UserVm = ViewModelProvider(this).get(UserViewModel::class.java)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        getdatauser()

        initPenumpangAdapter()

        binding.btnLanjut.setOnClickListener {
            val idDeparture = HomeVm.getIdDep()
            val idReturn = HomeVm.getIdReturn()
            val saveidDeparture = HomeVm.saveIdTicket(idDeparture!!)
            val saveidReturn = HomeVm.saveIdTicket(idReturn!!)
            val adult = HomeVm.getPassengerDewasa()
            val child = HomeVm.getPassengerAnak()
            val baby = HomeVm.getPassengerBayi()
            val total = adult + child + baby

            val dataList = CheckVm.getDataList()
            Log.d("Hasil Pencarian", "$dataList")


            val penumpangData = PenumpangRoundTripRequest(idDeparture!!,idReturn!! ,dataList)

            val token = pref.getString("token", "").toString()
            CheckoutVm.postbiopemesan(token,penumpangData)
            CheckoutVm.livebiopemesanan.observe(viewLifecycleOwner) {
                if (it.status == true) {
                    Toast.makeText(
                        requireContext(),
                        "Successfully Added Passenger Data",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(R.id.action_checkBioPenumpangFragment_to_checkoutFragment)

                }
            }
        }

        binding.switched.setOnCheckedChangeListener { p0, isChecked ->

            if (isChecked) {
                binding.tvNamaKeluarga.visibility = View.VISIBLE
                binding.txtNamaKeluarga.visibility = View.VISIBLE

            } else {
                binding.tvNamaKeluarga.visibility = View.GONE
                binding.txtNamaKeluarga.visibility = View.GONE

            }
        }



    }

    private fun initPenumpangAdapter() {
        val dewasa = HomeVm.getPassengerDewasa()
        val anak = HomeVm.getPassengerAnak()
        val bayi = HomeVm.getPassengerBayi()
        val total = dewasa + anak + bayi
        val jumlahDewasa = HomeVm.getPassengerDewasa()

        val Listpenumpang: ArrayList<Penumpang> = ArrayList()
        //get Penumpang Dewasa
        for (i in 1..dewasa) {
            Listpenumpang.add(Penumpang("Dewasa $i"))
        }
        //get Penumpang Anak
        for (i in 1..anak){
            Listpenumpang.add(Penumpang("Anak $i"))
        }
        //get Penumpang Bayi
        for(i in 1..bayi){
            Listpenumpang.add(Penumpang("Bayi $i"))
        }



        binding.rvPenumpang.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            penumpangAdapter = PenumpangAdapter(Listpenumpang)
            adapter = penumpangAdapter
            penumpangAdapter.setOnItemClickListener(object : PenumpangAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
                    Toast.makeText(requireContext(), "posisi card ini $position", Toast.LENGTH_SHORT).show()
                    val bundle = Bundle()
                    val name = Listpenumpang[position].penumpang
                    bundle.putInt("index",position)
                    bundle.putString("penumpang",name)
                    findNavController().navigate(R.id.action_checkBioPenumpangFragment_to_detailBiodataPenumpangFragment,bundle)
                }
            })
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }


    fun getdatauser(){
        val token = pref.getString("token", "").toString()
        UserVm.userprofile(token)
        UserVm.getProfile.observe(viewLifecycleOwner){
            Log.d("Profile","email : ${it.email}")
            binding.tvEmailPemesan.setText(it.email)
            binding.tvNoTelfonPemesan.setText(it.telephone)
            binding.tvNamaLengkapPemesan.setText(it.fullName)
        }

    }
}