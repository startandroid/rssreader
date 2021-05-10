package com.startandroid.rssreader.item.preview.adapter

import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.domain.dto.Item
import com.startandroid.rssreader.databinding.FeedItemRowBinding

class ItemHolder(private val binding: FeedItemRowBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.textViewTitle.text = item.title
        binding.textViewDescription.text = HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
}