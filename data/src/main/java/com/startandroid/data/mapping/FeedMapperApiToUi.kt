package com.startandroid.data.mapping

import com.startandroid.data.api.FeedApi
import com.startandroid.domain.dto.Feed
import javax.inject.Inject

class FeedMapperApiToUi @Inject constructor(
    val itemMapperApiToUi: ItemMapperApiToUi
) : Mapper<FeedApi, Feed>() {
    override fun map(input: FeedApi): Feed {
        return input.channel.run {
            Feed(
                title = title,
                description = description,
                imageUrl = image.url,
                itemList = itemMapperApiToUi.mapList(itemList)
            )
        }
    }

}