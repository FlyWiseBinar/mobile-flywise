package com.binar.projekakhir.view.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.projekakhir.view.adapter.FavouriteAdapter
import com.binar.projekakhir.viewmodel.FavoriteViewModel
import com.binar.projekakhir.viewmodel.HomeViewModel
import com.binar.projekakhir.viewmodel.UserViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
//    private var tanggalKembali: String? = null
    private lateinit var pref : SharedPreferences
    private var tanggalKembali = "Tanggal"
    private var tanggalPergi:String? = null
    private lateinit var HomeVm : HomeViewModel
    private val userVm : UserViewModel by viewModels()
    private lateinit var adapter: FavouriteAdapter
    private val favVm: FavoriteViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomeVm = ViewModelProvider(this).get(HomeViewModel::class.java)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)


        //get date
        val dateNowReturn = HomeVm.getArrivedDate()
        val dateNowDeparture = HomeVm.getDepartureDate()
        //set date
        binding.tanggalreturn.setText(dateNowReturn)
        binding.tanggaldeparture.setText(dateNowDeparture)

        binding.btnCariPenerbangan.setOnClickListener {
            val isSwitchTrue = HomeVm.getCheckedSwitch()
            if (isSwitchTrue){
                findNavController().navigate(R.id.action_homeFragment2_to_hasilPencarianFragment)
            } else{
                findNavController().navigate(R.id.action_homeFragment2_to_hasilPencarianFragment)
            }

        }

        binding.swPp.setOnCheckedChangeListener { p0, isChecked ->

            if (isChecked) {
                HomeVm.saveselected(true)
                binding.returndate.visibility = View.VISIBLE

            } else {
                HomeVm.saveselected(false)
                binding.returndate.visibility = View.GONE

            }
        }

        val getCheck = HomeVm.getCheckedSwitch()
        Log.d("Beranda Fragment","$getCheck")
        binding.swPp.isChecked = getCheck




        binding.layoutDeparture.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_pilihDestinasiFromFragment)
        }

        binding.layoutArrival.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_pilihDestinasiToFragment)
        }





        getSetPenumpang()

        getfavoritemodel()
        datePickerReturn()
        datePickerDeparture()
        getTanggalKembali()
        getCityFrom()
        getCityTo()


        getpilihtanggal()

       getKelasPenerbangan()

        binding.passenger.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_setPenumpangFragment)
        }

        binding.setclass.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_setKelasFragment2)
        }

        pref = requireContext().getSharedPreferences("MyPrefsFrom", Context.MODE_PRIVATE)
        val selectedDestination = pref.getString("keyFrom","")
        binding.from.text = selectedDestination

        val prefTo = requireContext().getSharedPreferences("MyPrefsTo", Context.MODE_PRIVATE)
        val selectedDestinationTo = prefTo.getString("keyTo","")
        binding.to.text = selectedDestinationTo


    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun datePickerDeparture() {
        binding.tanggaldeparture.setOnClickListener {
            val nameMonth = namaMonth()
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val bulan =nameMonth[month]
                    tanggalPergi = "$dayOfMonth $bulan+1 $year"
                    binding.tanggaldeparture.setText(tanggalPergi)
                },
                year, month, day,
            )
            datePickerDialog.show()
            datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                val bulanDeparture = nameMonth[datePicker.month]
                val month = datePicker.month
                val tahunDeparture = datePicker.year
                val hariDeparture = datePicker.dayOfMonth
                val tanggalDeparture = "$tahunDeparture-${month+1}-$hariDeparture"
                HomeVm.saveDepartureDate(tanggalDeparture)
                findNavController().navigate(R.id.homeFragment2)
            }
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.DARKBLUE05))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.DARKBLUE05))

        }
    }

    //get city to and from

    private fun getTanggalKembali() {
        if (tanggalKembali == null) {
            binding.tanggaldeparture.setText("Pilih Tanggal")
        } else {
            binding.tanggaldeparture.setText(tanggalKembali)
            binding.tanggaldeparture.setTextColor(resources.getColor(R.color.black))

        }


    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun datePickerReturn(){
        binding.tanggalreturn.setOnClickListener {
            val nameMonth = namaMonth()

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                val bulan =nameMonth[month]
                tanggalPergi = "$dayOfMonth $bulan+1 $year"
                binding.tanggalreturn.text = tanggalPergi
            },
                year, month, day,
            )
            datePickerDialog.show()
            datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                val nameMonth = namaMonth()
                val bulan =nameMonth[datePicker.month]
                val month = datePicker.month
                val tahun = datePicker.year
                val hari = datePicker.dayOfMonth
                val tanggalPilihan = "$tahun-${month+1}-$hari"
               HomeVm.saveDatePref(tanggalPilihan)
                findNavController().navigate(R.id.homeFragment2)
            }
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.DARKBLUE05))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.DARKBLUE05))
        }
    }

    private fun getpilihtanggal(){
        if (tanggalKembali == null) {
            binding.tanggalreturn.setText("Pilih Tanggal")
        } else {
            binding.tanggalreturn.setText(tanggalKembali)
            binding.tanggalreturn.setTextColor(resources.getColor(R.color.black))

        }
    }

    private fun getfavoritemodel(){
        favVm.getfavorite()
        favVm.livedatafavorite.observe(viewLifecycleOwner, Observer {
            binding.destinationfavourite.layoutManager = GridLayoutManager(context,2)
            if (it!= null) {
                binding.destinationfavourite.adapter = FavouriteAdapter(it)
            }
        })
    }

    private fun namaMonth(): ArrayList<String> {
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
        return nameMonth
    }




    private fun getSetPenumpang(){
        val dewasa = HomeVm.getPenumpangDewasa()
        val anak = HomeVm.getPenumpangAnak()
        val bayi = HomeVm.getPenumpangBayi()
        val total = dewasa + anak + bayi
        binding.passenger.text = "$total Penumpang"
    }

    private fun getKelasPenerbangan() {
        val kelas = HomeVm.getNamaKelas()
        binding.setclass.text = kelas
    }

    private fun getCityTo() {
        val destination = HomeVm.getCityTo()
        binding.to.text = destination
    }

    private fun getCityFrom() {
        val departure = HomeVm.getCityFrom()
        binding.from.text =  departure
    }




}