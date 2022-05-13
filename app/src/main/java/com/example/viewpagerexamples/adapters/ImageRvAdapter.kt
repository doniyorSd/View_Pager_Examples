package com.example.viewpagerexamples.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewpagerexamples.R
import com.example.viewpagerexamples.databinding.ItemImageBinding

class ImageRvAdapter(
    var list: ArrayList<String>,
    var context: Context,
    var myClickInterface: MyClickInterface
) :
    RecyclerView.Adapter<ImageRvAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(url: String, position: Int) {
            val bind = ItemImageBinding.bind(itemView)
            Glide.with(context).load(url).into(bind.iv)

            bind.root.setOnClickListener {
                myClickInterface.onClick(url, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface MyClickInterface {
        fun onClick(url: String, position: Int)
    }
}