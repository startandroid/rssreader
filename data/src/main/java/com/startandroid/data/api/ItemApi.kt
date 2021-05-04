package com.startandroid.data.api

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class ItemApi @JvmOverloads constructor(
    @field: Element(name = "title", required = false)
    var title: String = "",

    @field: Element(name = "description", required = false)
    var description: String = "",

    @field: Element(name = "featuredImage", required = false)
    var featuredImage: String = "",

    @field: Element(name = "link", required = false)
    var link: String = ""
)