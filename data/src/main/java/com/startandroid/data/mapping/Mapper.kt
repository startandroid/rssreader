package com.startandroid.data.mapping

abstract class Mapper<I,O> {

    abstract fun map(input: I): O

    fun mapList(inputList: Collection<I>): List<O> {
        return inputList.map { map(it) }
    }

}