package com.binar.projekakhir.view.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var tanggalKembali: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        datePickerReturn()
        getTanggalKembali()

        binding.passenger.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_setPenumpangFragment)
        }


    }

    private fun datePickerReturn() {
        binding.tanggal.setOnClickListener {
            val nameMonth = ArrayList<String>()
            nameMonth.add("Januari")
            nameMonth.add("Februari")
            nameMonth.add("Maret")
            nameMonth.add("April")
            nameMonth.add("Mei")
            nameMonth.add("Juni")
            nameMonth.add("Juli")
            nameMonth.add("Agustus")
            nameMonth.add("September")
            nameMonth.add("Oktober")
            nameMonth.add("November")
            nameMonth.add("Desember")

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val bulan = nameMonth[month]
                    tanggalKembali = "$dayOfMonth $bulan $year"
                    binding.tanggal.setText(tanggalKembali)
                    binding.tanggal.setTextColor(resources.getColor(R.color.black))
                },
                year, month, day,
            )
            datePickerDialog.show()

        }
    }

    private fun getTanggalKembali() {
        if (tanggalKembali == null) {
            binding.tanggal.setText("Pilih Tanggal")
        } else {
            binding.tanggal.setText(tanggalKembali)
            binding.tanggal.setTextColor(resources.getColor(R.color.black))

        }


    }
}