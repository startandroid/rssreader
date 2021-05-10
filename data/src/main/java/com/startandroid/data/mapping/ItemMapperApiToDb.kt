package com.startandroid.data.mapping

import com.startandroid.data.api.ItemApi
import com.startandroid.data.db.ItemDb
import java.util.*
import javax.inject.Inject

class ItemMapperApiToDb @Inject constructor(private val timeFormatter: TimeFormatter) : Mapper<ItemApi, ItemDb>() {
    override fun map(input: ItemApi): ItemDb {
        return input.run {
            ItemDb(
                link = link,
                feedUrl = "",
                title = title,
                description = description,
                imageUrl = featuredImage,
                datetime = timeFormatter.parseItemPubDate(pubDate)
            )
        }
    }

}