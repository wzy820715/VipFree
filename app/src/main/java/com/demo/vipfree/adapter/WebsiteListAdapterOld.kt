package com.demo.vipfree.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.vipfree.R
import com.demo.vipfree.model.WebsiteInfo
import com.demo.vipfree.ui.WebActivity

class WebsiteListAdapterOld() : RecyclerView.Adapter<WebsiteListAdapterOld.ItemViewHolder>() {

    private lateinit var mList: MutableList<WebsiteInfo>

    constructor(mList: MutableList<WebsiteInfo>) : this() {
        this.mList = mList
    }

    fun update(data: List<WebsiteInfo>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val repoData = mList[position]
        holder.bindData(repoData)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var icon: ImageView
        var name: TextView
        lateinit var info: WebsiteInfo

        init {
            itemView.apply {
                icon = findViewById<View>(R.id.iv_icon) as ImageView
                name = findViewById<View>(R.id.tv_name) as TextView
                setOnClickListener {
                    val intent = Intent(itemView.context, WebActivity::class.java)
                    var bundle = Bundle()
                    bundle.putParcelable("info",info)
                    intent.putExtras(bundle)
                    itemView.context.startActivity(intent)
                }
            }

        }

        fun bindData(info: WebsiteInfo){
            this.info = info
            Glide.with(itemView.context)
                .load(info.icon)
                .into(icon)
            name.text = info.name
        }

        companion object {
            fun create(parent: ViewGroup): ItemViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_website, parent, false)
                return ItemViewHolder(view)
            }
        }
    }
}