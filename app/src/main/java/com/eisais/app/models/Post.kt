package com.eisais.app.models

object PostModel {
    data class Result(val  data: Data, val status: Int)
    data class Data(val response: List<Response>)
    data class Response(
        val subject_category: SubjectCategory? = null,
        val id: Int? = null,
        val title: String? = null,
        val mini_body: String? = null,
        val body: String? = null,
        val author: String? = null,
        val date: String? = null
    )
    data class SubjectCategory(
        val id: Int? = null,
        val title: String? = null
    )
}