package com.demo.vipfree.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.vipfree.R
import com.demo.vipfree.ui.WebActivity
import com.demo.vipfree.model.WebsiteInfo
import com.demo.vipfree.databinding.LayoutItemWebsiteBinding

class WebsiteListAdapter : ListAdapter<WebsiteInfo, WebsiteListAdapter.ItemViewHolder>(REPO_COMPARATOR)  {

    /**
     * 通用继承 RecyclerView.Adapter 的写法见 WebsiteListAdapterOld 类
     * ListAdapter 使用注意事项详见 https://blog.csdn.net/willway_wang/article/details/109263647
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val repoData = getItem(position)
        holder.bindData(repoData)
    }

    class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_website, parent, false)){
        private val binding = LayoutItemWebsiteBinding.bind(itemView)
        private val icon = binding.ivIcon
        private val name = binding.tvName
        lateinit var info: WebsiteInfo

        init {
            itemView.setOnClickListener {
                    val intent = Intent(itemView.context, WebActivity::class.java)
                    var bundle = Bundle()
                    bundle.putParcelable("info",info)
                    intent.putExtras(bundle)
                    itemView.context.startActivity(intent)
                }
        }

        fun bindData(info: WebsiteInfo){
            this.info = info
            Glide.with(itemView.context)
                .load(info.icon)
                .into(icon)
            name.text = info.name
        }

    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<WebsiteInfo>() {
            override fun areItemsTheSame(oldItem: WebsiteInfo, newItem: WebsiteInfo): Boolean =
                oldItem.name == newItem.name


            override fun areContentsTheSame(oldItem: WebsiteInfo, newItem: WebsiteInfo): Boolean =
                oldItem == newItem
        }
    }
}