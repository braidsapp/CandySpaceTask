package com.example.candyspace

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    //@GET("/2.3/users?order=desc&sort=name&site=stackoverflow")
    @GET("/2.3/users?order=desc&sort=reputation&site=stackoverflow")
    suspend fun getUsers(): Response<ListOfUsers>
}