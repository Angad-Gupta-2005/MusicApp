package com.angad.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("x-rapidapi-key: 92b8a40cd3mshe77777a773fba08p1205a0jsnd9b2262395e0",
      "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<List<MyData>>
}