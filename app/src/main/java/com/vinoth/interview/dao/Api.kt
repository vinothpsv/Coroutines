package com.vinoth.interview.dao

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @get:GET("posts")
    val posts: Call<List<Posts>>

    @get:GET("users")
    val users: Call<List<Root>>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}