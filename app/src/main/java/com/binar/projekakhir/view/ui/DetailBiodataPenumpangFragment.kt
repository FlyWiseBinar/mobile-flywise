package com.binar.projekakhir.view.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentDetailBiodataPenumpangBinding
import com.binar.projekakhir.model.checkout.PenumpangPost
import com.binar.projekakhir.viewmodel.CheckViewModel
import java.util.*


class DetailBiodataPenumpangFragment : Fragment() {

    private lateinit var binding : FragmentDetailBiodataPenumpangBinding
    private lateinit var pref: SharedPreferences
    private val CheckVM:CheckViewModel by activityViewModels()
    private var titleAd:String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBiodataPenumpangBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        val penumpang = arguments?.getString("penumpang")
        val indexPenumpang = arguments?.getInt("index")
        val hintTitle = resources.getStringArray(R.array.gender)
        binding.tvToolbar.text = penumpang

        val dataList = CheckVM.getDataList()
        formTitle(hintTitle)

        if (dataList.isNotEmpty()){
            if (indexPenumpang in dataList.indices){
                Log.d("Data","${dataList.indices}")
                Log.d("Data","${dataList[0]}")
                val itemPenumpang = dataList[indexPenumpang!!]
                binding.edtTitle.setText(itemPenumpang.title)
                binding.edtNamaLengkap.setText(itemPenumpang.name)
//                binding.edtNamaKeluargaPemesan.setText(itemPenumpang.familyName)
                binding.edtDateOfBirth.setText(itemPenumpang.dateofbirth)
                binding.edtKewarganegaraan.setText(itemPenumpang.citizenship)
                binding.edtKtp.setText(itemPenumpang.ktppaspor)
                editData(dataList, indexPenumpang)
            } else {
                sumbitData()
            }
        } else {
            sumbitData()
        }

        binding.btnSwitch.setOnCheckedChangeListener { p0, isChecked ->

            if (isChecked) {
                binding.tvNamaKeluargaPemesan.visibility = View.VISIBLE
                binding.edtNamaKeluargaPemesan.visibility = View.VISIBLE

            } else {
                binding.tvNamaKeluargaPemesan.visibility = View.GONE
                binding.edtNamaKeluargaPemesan.visibility = View.GONE

            }
        }

        binding.edtDateOfBirth.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val tanggalLahir = "$year-${month+1}-$dayOfMonth"
                    binding.edtDateOfBirth.setText(tanggalLahir)
                },
                year, month, day,
            )
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
        }


    }

    private fun editData(
        dataList: List<PenumpangPost>,
        indexPenumpang: Int?
    ) {
        binding.btnSimpan.setOnClickListener {
            Log.d("Detail Biodat", "On Clicckkk")
            val title = binding.edtTitle.text.toString()
            val name = binding.edtNamaLengkap.text.toString()
            val namaKeluarga = binding.edtNamaKeluargaPemesan.text.toString()
            val tanggalLahir = binding.edtDateOfBirth.text.toString()
            val kewarganegaraan = binding.edtKewarganegaraan.text.toString()
            val ktp = binding.edtKtp.text.toString()

            Log.d("detail biodata", "$title")

            dataList[indexPenumpang!!].title = title
            dataList[indexPenumpang].name = name
//            dataList[indexPenumpang].familyName = namaKeluarga
            dataList[indexPenumpang].dateofbirth = tanggalLahir
            dataList[indexPenumpang].citizenship = kewarganegaraan
            dataList[indexPenumpang].ktppaspor = ktp

            findNavController().navigate(R.id.action_detailBiodataPenumpangFragment_to_checkBioPenumpangFragment)
        }
    }

    private fun sumbitData() {
        binding.btnSimpan.setOnClickListener {

            Log.d("Detail Biodat", "On Clicckkk Submit Data")

//            val title = binding.edtTitle.text.toString()
            val name = binding.edtNamaLengkap.text.toString()
            val namaKeluarga = binding.edtNamaKeluargaPemesan.text.toString()
            val tanggalLahir = binding.edtDateOfBirth.text.toString()
            val kewarganegaraan = binding.edtKewarganegaraan.text.toString()
            val ktp = binding.edtKtp.text.toString()

            val dataPenumpang =
                PenumpangPost(ktp, tanggalLahir, kewarganegaraan, name, titleAd!!)
            CheckVM.addData(dataPenumpang)
            findNavController().navigate(R.id.action_detailBiodataPenumpangFragment_to_checkBioPenumpangFragment)

        }
    }

    private fun formTitle(hintTitle: Array<String>) {
        binding.edtTitle.apply {
            val adapterTitle = ArrayAdapter(requireContext(), R.layout.dropdown_item, hintTitle)
            setAdapter(adapterTitle)
            hint = "Title"
            onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
                titleAd = adapterTitle.getItem(position).toString()
            }
        }
    }




}