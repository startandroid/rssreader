package com.startandroid.data.mapping

import com.startandroid.data.api.ItemApi
import com.startandroid.domain.dto.Item
import javax.inject.Inject

class ItemMapperApiToUi @Inject constructor() : Mapper<ItemApi, Item>() {
    override fun map(input: ItemApi): Item {
        return input.run {
            Item(
                title = title,
                description = description,
                imageUrl = featuredImage,
                link = link
            )
        }
    }

}