package com.startandroid.data.api

import org.simpleframework.xml.*
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@Root(name = "rss", strict = false)
class FeedApi @JvmOverloads constructor(
    @field: Element(name = "channel")
    var channel: Channel = Channel()
)

@Root(name = "channel", strict = false)
class Channel @JvmOverloads constructor(

    @field: Element(name = "title")
    var title: String = "",

    @field: Element(name = "description")
    var description: String = "",

    @field: Element(name = "image")
    var image: Image = Image(),

    @field: ElementList(inline = true)
    var itemList: List<ItemApi> = mutableListOf(),
)

@Root(name = "image", strict = false)
class Image @JvmOverloads constructor(

    @field: Element(name = "url", required = false)
    var url: String = "",

)






// https://stackoverflow.com/questions/62922995/android-retrofit-simplexmlconverter-in-kotlin

//class Rss(
//    @Attribute(name = "xmlns")
//    val version: String,
//
//    val channel: Channel
//)

//data class Channel(val description: String)
