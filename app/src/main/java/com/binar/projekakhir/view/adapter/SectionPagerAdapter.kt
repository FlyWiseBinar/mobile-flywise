package com.binar.projekakhir.view.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.binar.projekakhir.view.ui.DetailPenerbanganPulangFragment
import com.binar.projekakhir.view.ui.DetailPergiFragment

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = DetailPergiFragment()
            1 -> fragment = DetailPenerbanganPulangFragment()
        }
        return fragment as Fragment
    }
}