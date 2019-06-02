package com.eisais.app.models

object CategoryModel {
    data class Result(val data: Data, val status: Int)
    data class Data(val response: List<Response>)
    data class Response(
        val id: Int,
        val title: String
    )
}