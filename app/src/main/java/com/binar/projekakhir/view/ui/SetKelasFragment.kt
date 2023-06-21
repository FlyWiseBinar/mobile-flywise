package com.binar.projekakhir.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentSetKelasBinding
import com.binar.projekakhir.datauser.KelasDummy
import com.binar.projekakhir.view.adapter.SetKelasAdapter
import com.binar.projekakhir.viewmodel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetKelasFragment : BottomSheetDialogFragment(),
    SetKelasAdapter.OnItemClickListener{

    private lateinit var binding: FragmentSetKelasBinding
    private val berandaViewModel: HomeViewModel by viewModels()
    private lateinit var kelasList:ArrayList<KelasDummy>
    private lateinit var kelasAdapter:SetKelasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentSetKelasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnClose.setOnClickListener {
            dismiss()
        }

        kelasList = ArrayList()
        kelasList.add(KelasDummy("Economy","Rp.20000"))
        kelasList.add(KelasDummy("Premium Economy","Rp.20000"))
        kelasList.add(KelasDummy("Business","Rp.20000"))
        kelasList.add(KelasDummy("First Class","Rp.20000"))

        binding.rvSetKelas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            kelasAdapter = SetKelasAdapter(kelasList,this@SetKelasFragment)
            adapter = kelasAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }
    override fun onItemClick(position: Int, adapter: SetKelasAdapter, v: View) {
        val rvKelas = binding.rvSetKelas
        val itemClicked: KelasDummy = kelasList[position]
        itemClicked.isSelected = !itemClicked.isSelected
        if (itemClicked.isSelected){
            rvKelas.getChildAt(rvKelas.indexOfChild(v)).findViewById<ConstraintLayout>(R.id.layout_set_kelas)
            rvKelas.getChildAt(rvKelas.indexOfChild(v)).findViewById<ImageView>(R.id.succes_klik).visibility = View.VISIBLE
            rvKelas.getChildAt(rvKelas.indexOfChild(v)).findViewById<TextView>(R.id.tv_class).setTextColor(resources.getColor(R.color.white))
            rvKelas.getChildAt(rvKelas.indexOfChild(v)).findViewById<TextView>(R.id.tv_price).setTextColor(resources.getColor(R.color.white))

            //soon update using save args
            binding.btnSaveClass.setOnClickListener {

                berandaViewModel.saveKelasPreferences(itemClicked.kelas,2000,itemClicked.isSelected)
                findNavController().navigate(R.id.action_setKelasFragment_to_homeFragment2)
            }

            Log.d("Harga",itemClicked.harga)
            Log.d("Kelas", itemClicked.kelas)
            Log.d("Harga",itemClicked.harga)
        } else {
            rvKelas.getChildAt(rvKelas.indexOfChild(v)).findViewById<ConstraintLayout>(R.id.layout_set_kelas)
            rvKelas.getChildAt(rvKelas.indexOfChild(v)).findViewById<ImageView>(R.id.succes_klik).visibility = View.GONE
            rvKelas.getChildAt(rvKelas.indexOfChild(v)).findViewById<TextView>(R.id.tv_class).setTextColor(resources.getColor(R.color.black))
            rvKelas.getChildAt(rvKelas.indexOfChild(v)).findViewById<TextView>(R.id.tv_price)
        }
    }
}