package com.example.viewpagerexamples.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.viewpagerexamples.HomeFragment
import com.example.viewpagerexamples.models.ImageModel

class HomeAdapter(
    var list: ArrayList<ImageModel>,
    fragmentActivity: FragmentActivity
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        //1-usul newInstance
//        return HomeFragment.newInstance(list[position])
        // 2-usul
        val fragment = HomeFragment()
        val bundle = Bundle().apply {
            putSerializable("image", list[position])
        }
        fragment.arguments = bundle

        return fragment
    }
}