package com.startandroid.rssreader.item.preview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.domain.dto.Item
import com.startandroid.rssreader.databinding.FeedItemRowBinding
import javax.inject.Inject

class ItemAdapter @Inject constructor(): RecyclerView.Adapter<ItemHolder>() {

    private val _data = mutableListOf<Item>()

    fun setData(data: List<Item>?) {
        _data.apply {
            clear()
            data?.let { addAll(it) }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(FeedItemRowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int {
        return _data.size
    }

}