package com.startandroid.rssreader.item.list.adapter

import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.domain.dto.Item
import com.startandroid.rssreader.databinding.FeedItemRowBinding
import java.text.SimpleDateFormat
import java.util.*

class ItemHolder(private val binding: FeedItemRowBinding, private val onClick: ((String?) -> Unit)?) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item?) {
        binding.textViewTitle.text = item?.title
        binding.textViewDescription.text =
            HtmlCompat.fromHtml(item?.description ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.textViewTimestamp.text = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date(item?.datetime ?: 0))
        binding.root.setOnClickListener {
            Log.d("qweee", "onclick ${item?.link}")
            onClick?.invoke(item?.link)
        }
    }
}