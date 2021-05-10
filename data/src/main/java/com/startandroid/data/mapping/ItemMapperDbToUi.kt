package com.startandroid.data.mapping

import com.startandroid.data.api.ItemApi
import com.startandroid.data.db.ItemDb
import com.startandroid.domain.dto.Item
import javax.inject.Inject

class ItemMapperDbToUi @Inject constructor() : Mapper<ItemDb, Item>() {
    override fun map(input: ItemDb): Item {
        return input.run {
            Item(
                title = title,
                description = description,
                imageUrl = imageUrl,
                link = link,
                datetime = datetime
            )
        }
    }

}