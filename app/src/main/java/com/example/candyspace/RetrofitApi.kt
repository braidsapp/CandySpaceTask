package com.example.candyspace

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/2.3/users?order=asc&sort=name&site=stackoverflow")
    suspend fun getUsers(): Response<ListOfUsers>

    //@GET("/2.3/users?order=asc&sort=name&inname={searchPhrase}&site=stackoverflow")
    @GET("/2.3/users?order=asc&sort=name&site=stackoverflow")
    suspend fun getUsers(@Query("inname") searchPhrase: String): Response<ListOfUsers>

    // with keyword search: /2.3/users?order=asc&sort=name&inname=${keyword}&site=stackoverflow
    // without: /2.3/users?order=asc&sort=name&site=stackoverflow
}