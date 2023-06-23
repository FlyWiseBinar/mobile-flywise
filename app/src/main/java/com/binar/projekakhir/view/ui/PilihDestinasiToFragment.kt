package com.binar.projekakhir.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binar.projekakhir.R
import com.binar.projekakhir.databinding.FragmentPilihDestinasiToBinding
import com.binar.projekakhir.model.searchairport.data
import com.binar.projekakhir.view.adapter.PencarianToAdapter
import com.binar.projekakhir.view.adapter.RiwayatPencarianAdapter
import com.binar.projekakhir.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PilihDestinasiToFragment : Fragment() {

    private lateinit var binding : FragmentPilihDestinasiToBinding
    private lateinit var homeVM: HomeViewModel
    private lateinit var sharedPreferences: SharedPreferences

    private val PREF_NAME = "SearchHistory"
    private val KEY_HISTORY = "search_history"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPilihDestinasiToBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVM = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        binding.ivSearch.setOnClickListener {
            val kotaSearch = binding.etSearch.text.toString()
            getSearch(requireContext(), kotaSearch)
        }
        binding.ivClose.setOnClickListener {
            findNavController().navigate(R.id.action_pilihDestinasiToFragment_to_homeFragment2)
        }
        binding.tvDelete.setOnClickListener {
            clearSearchHistory(requireContext(), "city")
        }
        sharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        showSearchHistory(requireContext())
    }

    private fun getSearch(context: Context, city: String) {
        val searchText = binding.etSearch.text.toString().trim()
        if (searchText.isNotEmpty()) {
            homeVM.callGetSearchAirport(city)
            homeVM.search.observe(viewLifecycleOwner) { searchResults ->
                val filteredResults = searchResults?.filter { dat ->
                    dat.city.contains(searchText, ignoreCase = true)
                }
                showSearch(context, filteredResults ?: emptyList())
                saveSearchHistory(searchText)
            }
        }
    }
    private fun showSearch(context: Context, dataAirport: List<data>) {
        val searchAdapter = PencarianToAdapter(context, dataAirport)
        binding.rvCity.adapter = searchAdapter
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvCity.layoutManager = layoutManager
    }

    private fun saveSearchHistory(searchText: String) {
        val searchHistory = getSearchHistory().toMutableList()
        searchHistory.add(searchText)

        val editor = sharedPreferences.edit()
        editor.putStringSet(KEY_HISTORY, searchHistory.toSet())
        editor.apply()
    }

    private fun getSearchHistory(): Set<String> {
        return sharedPreferences.getStringSet(KEY_HISTORY, emptySet()) ?: emptySet()
    }

    private fun showSearchHistory(context: Context) {
        val searchHistory = getSearchHistory().toList()
        val searchHistoryAdapter = RiwayatPencarianAdapter(context, searchHistory)
        binding.rvCity.adapter = searchHistoryAdapter
        binding.rvCity.layoutManager = LinearLayoutManager(context)

        searchHistoryAdapter.onItemClick = { searchText ->
            binding.etSearch.setText(searchText)
            getSearch(context, searchText)
        }
    }

    private fun clearSearchHistory(context: Context, city: String) {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_HISTORY)
        editor.apply()

        // Menghapus riwayat pencarian
        showSearchHistory(context)
    }
}