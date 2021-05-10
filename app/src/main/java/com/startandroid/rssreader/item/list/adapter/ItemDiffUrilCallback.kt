package com.startandroid.rssreader.item.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.startandroid.domain.dto.Item
import javax.inject.Inject

class ItemDiffUrilCallback @Inject constructor(): DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.link == newItem.link
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

}
