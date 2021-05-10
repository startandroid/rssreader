package com.startandroid.rssreader.feed.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.startandroid.domain.dto.Feed
import com.startandroid.rssreader.databinding.FeedRowBinding

class FeedHolder(private val binding: FeedRowBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(feed: Feed) {
        binding.run {
            textViewTitle.text = feed.title
            textViewDescription.text = feed.description
        }
    }

}