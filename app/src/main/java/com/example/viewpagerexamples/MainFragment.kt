package com.example.viewpagerexamples

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer
import com.example.viewpagerexamples.adapters.HomeAdapter
import com.example.viewpagerexamples.databinding.FragmentMainBinding
import com.example.viewpagerexamples.databinding.ItemTabBinding
import com.example.viewpagerexamples.models.ImageModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var list: ArrayList<ImageModel>
    lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        val binding = FragmentMainBinding.bind(view)

        setData()
        adapter = HomeAdapter(list, requireActivity())

        binding.viewPager.adapter = adapter

        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            val inf = layoutInflater.inflate(R.layout.item_tab, binding.root, false)
            val bind = ItemTabBinding.bind(inf)
            bind.tv.text = list[position].title
            tab.customView = inf

            if (position == 0) {
                bind.indicator.visibility = View.VISIBLE
                bind.tv.setTextColor(Color.WHITE)
            } else {
                bind.indicator.visibility = View.INVISIBLE
                bind.tv.setTextColor(Color.DKGRAY)
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                val customView = tab?.customView
                val bind = ItemTabBinding.bind(customView!!)

                bind.indicator.visibility = View.VISIBLE
                bind.tv.setTextColor(Color.WHITE)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val bind = ItemTabBinding.bind(customView!!)

                bind.indicator.visibility = View.INVISIBLE
                bind.tv.setTextColor(Color.DKGRAY)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.viewPager.setPageTransformer { page, position ->
            CubeOutTransformer().transformPage(page, position)
        }

        return view
    }

    private fun setData() {
        list = ArrayList()

        val images = ArrayList<String>()
        for (i in 0 until 50) {
            images.add("https://my4kwallpapers.com/wp-content/uploads/2020/09/Wallpaper-Nature-4k.jpg")
            images.add("https://lookw.ru/9/945/1566941301-oboi-008.jpg")
            images.add("https://s1.1zoom.ru/big0/66/349676-svetik.jpg")
        }
        list.add(
            ImageModel("ALL", images)
        )
        list.add(
            ImageModel("NEW", images)
        )
        list.add(
            ImageModel("ANIMALS", images)
        )
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}