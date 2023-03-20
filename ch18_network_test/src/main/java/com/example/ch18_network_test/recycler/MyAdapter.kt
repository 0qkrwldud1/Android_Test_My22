package com.example.ch18_network_test.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch18_network_test.databinding.ItemMainBinding
import com.example.ch18_network_test.model.ItemModel


class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: MutableList<ItemModel>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

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
        binding.itemDesc3.text = model.ITEMCNTNTS
        Glide.with(context)
            .load(model.MAIN_IMG_THUMB)
            .into(binding.itemImage)

    }
}