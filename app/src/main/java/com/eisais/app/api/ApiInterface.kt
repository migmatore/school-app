package com.eisais.app.api

import com.eisais.app.models.CategoryModel
import com.eisais.app.models.PostModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("post")
    fun getPosts(): Observable<PostModel.Result>

    @GET("subject")
    fun getSubjects(): Observable<CategoryModel.Result>

    @GET("subject/{id}")
    fun getSubjectPosts(@Path("id") id: String): Observable<PostModel.Result>
}