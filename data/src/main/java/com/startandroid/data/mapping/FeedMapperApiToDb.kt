package com.startandroid.data.mapping

import com.startandroid.data.api.FeedApi
import com.startandroid.data.db.FeedDb
import com.startandroid.domain.dto.Feed
import javax.inject.Inject

class FeedMapperApiToDb @Inject constructor() : Mapper<FeedApi, FeedDb>() {
    override fun map(input: FeedApi): FeedDb {
        return input.channel.run {
            FeedDb(
                url = feedUrl,
                title = title,
                description = description,
                imageUrl = image.url
            )
        }
    }

}