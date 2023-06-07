package com.binar.projekakhir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.binar.projekakhir.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentcontainer)
        navController = navHostFragment!!.findNavController()

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.splashFragment,R.id.loginFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                } R.id.homeFragment2,R.id.riwayatFragment2 ,R.id.notifikasiFragment2, R.id.profileFragment2 -> {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else -> {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
            }
        }

        binding.bottomNavigation.setupWithNavController(navController)
    }


}