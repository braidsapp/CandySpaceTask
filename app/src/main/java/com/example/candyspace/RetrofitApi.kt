package com.example.candyspace

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/2.3/users?order=asc&sort=name&site=stackoverflow")
    suspend fun getUsers(): Response<ListOfUsers>

    //@GET("/2.3/users?order=asc&sort=name&inname={searchPhrase}&site=stackoverflow")
    @GET("/2.3/users?order=asc&sort=name&site=stackoverflow")
    suspend fun getUsers(@Query("inname") searchPhrase: String): Response<ListOfUsers>




    // with keyword search: /2.3/users?order=asc&sort=name&inname=${keyword}&site=stackoverflow
    // without: /2.3/users?order=asc&sort=name&site=stackoverflow

    //For user details:     /2.3/users/${22656}?site=stackoverflow


    @GET("/2.3/users/{userId}?site=stackoverflow")
    suspend fun getUserInfo(@Path("userId") userId: Int): Response<UserDetails>

    //TOP TAGS
    @GET("/2.3/users/{userId}/top-tags?site=stackoverflow")
    suspend fun getUserTopTags(@Path("userId") userId: Int): Response<TopTagList>
}