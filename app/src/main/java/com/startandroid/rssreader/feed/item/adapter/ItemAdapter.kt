package com.startandroid.rssreader.feed.item.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.domain.dto.Item
import com.startandroid.rssreader.databinding.FeedItemBinding
import javax.inject.Inject

class ItemAdapter @Inject constructor(): RecyclerView.Adapter<ItemHolder>() {

    private val _data: MutableList<Item> = mutableListOf()

    fun setData(data: List<Item>?) {
        _data.apply {
            clear()
            data?.let { addAll(it) }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(FeedItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int {
        return _data.size
    }

}