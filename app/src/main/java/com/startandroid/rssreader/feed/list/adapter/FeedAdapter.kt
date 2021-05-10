package com.startandroid.rssreader.feed.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.domain.dto.Feed
import com.startandroid.rssreader.databinding.FeedRowBinding
import javax.inject.Inject

class FeedAdapter @Inject constructor(): RecyclerView.Adapter<FeedHolder>() {

    private val _data = mutableListOf<Feed>()

    fun setData(data: List<Feed>?) {
        _data.apply {
            clear()
            data?.let { addAll(it) }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder {
        return FeedHolder(FeedRowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FeedHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int {
        return _data.size
    }

}