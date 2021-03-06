package com.startandroid.data.mapping

import com.startandroid.data.api.FeedApi
import com.startandroid.domain.dto.Feed
import javax.inject.Inject

class FeedMapperApiToUi @Inject constructor() : Mapper<FeedApi, Feed>() {
    override fun map(input: FeedApi): Feed {
        return input.channel.run {
            Feed(
                url = feedUrl,
                title = title,
                description = description,
                imageUrl = image.url
            )
        }
    }

}