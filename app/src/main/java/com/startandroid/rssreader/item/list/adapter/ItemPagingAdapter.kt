package com.startandroid.rssreader.item.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.startandroid.domain.dto.Item
import com.startandroid.rssreader.databinding.FeedItemRowBinding
import javax.inject.Inject

class ItemPagingAdapter @Inject constructor(diffCallback: ItemDiffUrilCallback):
    PagingDataAdapter<Item, ItemHolder>(diffCallback) {

    var onClick: ((String?) -> Unit)? = null

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(FeedItemRowBinding.inflate(LayoutInflater.from(parent.context)), onClick)
    }

}