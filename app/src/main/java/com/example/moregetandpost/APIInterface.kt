package com.example.moregetandpost

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("contacts/")
    fun getUsers() : Call<ArrayList<NameItem>>

    @POST("contacts/")
    fun addUsers(@Body userData:NameItem): Call<NameItem>
}
