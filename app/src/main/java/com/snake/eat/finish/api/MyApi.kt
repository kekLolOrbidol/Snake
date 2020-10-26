package com.snake.eat.finish.api


import retrofit2.Call
import retrofit2.http.GET

interface MyApi {
    @GET(".")
    fun getModel() : Call<Model>
}