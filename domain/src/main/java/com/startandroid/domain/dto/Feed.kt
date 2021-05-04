package com.startandroid.domain.dto

data class Feed(
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val itemList: List<Item> = emptyList()
)