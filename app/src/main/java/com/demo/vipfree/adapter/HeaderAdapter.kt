package com.demo.vipfree.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.vipfree.R
import com.demo.vipfree.databinding.LayoutHeaderBinding

class HeaderAdapter(private val bgColor : Int = Color.parseColor("#ff45b97c")) :
    RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder(parent, bgColor)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
    }

    override fun getItemCount() = 1

    class HeaderViewHolder(parent: ViewGroup, bgColor : Int) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_header, parent, false)) {
        private val binding = LayoutHeaderBinding.bind(itemView)
        init {
            binding.layoutBg.setBackgroundColor(bgColor)
        }
    }
}