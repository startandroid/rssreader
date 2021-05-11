package com.startandroid.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val link: String = "",
    val datetime: String = ""
)