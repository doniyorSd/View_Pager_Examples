package com.example.viewpagerexamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.viewpagerexamples.adapters.ImageRvAdapter
import com.example.viewpagerexamples.databinding.FragmentHomeBinding
import com.example.viewpagerexamples.models.ImageModel

class HomeFragment : Fragment() {

    lateinit var adapter: ImageRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val bind = FragmentHomeBinding.bind(view)

        val imageModel = arguments?.getSerializable("image") as ImageModel
        adapter = ImageRvAdapter(
            imageModel.images,
            requireContext(),
            object : ImageRvAdapter.MyClickInterface {
                override fun onClick(url: String, position: Int) {
                    val bundle = Bundle().apply {
                        putString("image", url)
                    }
                    findNavController().navigate(R.id.imageFragment,bundle)
                }
            })

        bind.rv.adapter = adapter
        return view
    }
}