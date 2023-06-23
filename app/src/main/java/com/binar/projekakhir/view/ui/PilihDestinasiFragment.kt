package com.binar.projekakhir.view.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentPilihDestinasiBinding
import com.binar.projekakhir.view.adapter.PencarianKotaAdapter
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PilihDestinasiFragment : Fragment() {

    private lateinit var binding : FragmentPilihDestinasiBinding
    private val HomeVm:HomeViewModel by viewModels()
    private lateinit var adapter : PencarianKotaAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPilihDestinasiBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromCity = arguments?.getString("from")

        val cityFrom = HomeVm.getCityFrom()
        val cityTo = HomeVm.getCityTo()
        val departure = HomeVm.getDepartureDate()
        val arrived = HomeVm.getArrivedDate()

        if (fromCity != null){
            HomeVm.cityfrom(cityFrom!!)
            HomeVm.livedatasearchallticket.observe(viewLifecycleOwner){
                val cityList : java.util.ArrayList<String> = ArrayList()
                for (i in it.indices){
                    cityList.add(it[i].originAirport.city)
                }
                val list = cityList.filter { element -> it.count { it.originAirport.city == element  } > 1 }.distinct()
                val cityAdapter = ArrayAdapter(requireContext(), R.layout.drop_pendaftaran, list)
                binding.rvDestination.adapter = cityAdapter
                binding.rvDestination.setOnItemClickListener{ parent, view, position, id ->
                    val selectedCity =  cityAdapter.getItem(position)
                    HomeVm.saveCityFrom(selectedCity!!)
                    findNavController().navigate(R.id.action_pilihDestinasiFragment_to_homeFragment2)
                }


                binding.searchView.addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        cityAdapter.filter.filter(p0)
                        if (p0?.isEmpty()!!){
                            binding.rvDestination.visibility = View.GONE
                        } else {
                            binding.rvDestination.visibility = View.VISIBLE
                        }

                    }

                    override fun afterTextChanged(p0: Editable?) {}

                })
            }
        } else {
            HomeVm.cityTo(cityTo!!)
            HomeVm.livedatasearchallticket.observe(viewLifecycleOwner){
                val cityList : java.util.ArrayList<String> = ArrayList()
                for (i in it.indices){
                    cityList.add(it[i].destinationAirport.city)
                }
                val list = cityList.filter { element -> it.count { it.destinationAirport.city == element  } > 1 }.distinct()
                val cityAdapter = ArrayAdapter(requireContext(), R.layout.drop_pendaftaran, list)
                binding.rvDestination.adapter = cityAdapter
                binding.rvDestination.setOnItemClickListener{ parent, view, position, id ->
                    val selectedCity =  cityAdapter.getItem(position)
                   HomeVm.saveCityTo(selectedCity!!)
                    findNavController().navigate(R.id.action_pilihDestinasiFragment_to_homeFragment2)
                }


                binding.searchView.addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        cityAdapter.filter.filter(p0)
                        if (p0?.isEmpty()!!){
                            binding.rvDestination.visibility = View.GONE
                        } else {
                            binding.rvDestination.visibility = View.VISIBLE
                        }

                    }

                    override fun afterTextChanged(p0: Editable?) {}

                })
            }
        }






    }


}