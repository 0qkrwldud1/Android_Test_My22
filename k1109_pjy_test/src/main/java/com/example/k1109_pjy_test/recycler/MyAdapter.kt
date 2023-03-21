package com.example.k1109_pjy_test.recycler

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.request.target.CustomTarget
import com.example.k1109_pjy_test.MainActivity
import com.example.k1109_pjy_test.databinding.ItemMainBinding
import com.example.k1109_pjy_test.model.Item

class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: MainActivity, val datas: MutableList<Item>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding

        //add......................................
        val model = datas!![position]
        binding.itemTitle.text = model.TITLE
        binding.itemTime.text = model.CNTCT_TEL
        binding.itemDesc1.text = model.GUGUN_NM
        binding.itemDesc2.text = model.HOMEPAGE_URL
        val urlImg = model.MAIN_IMG_THUMB

        Glide.with(context)
            .asBitmap()
            .load(urlImg)
            .into(object : CustomTarget<Bitmap>(100, 100) {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    binding.itemImage.setImageBitmap(resource)
                    Log.d("kk", "width : ${resource.width}, height: ${resource.height}")
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })

    }
}