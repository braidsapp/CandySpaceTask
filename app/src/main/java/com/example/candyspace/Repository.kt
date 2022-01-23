package com.example.candyspace

import retrofit2.Response

class Repository {
    suspend fun getUsers(): Response<ListOfUsers> {
        return RetrofitInstance.myApi.getUsers()
    }

    suspend fun getUsers(searchPhrase: String): Response<ListOfUsers> {
        return RetrofitInstance.myApi.getUsers(searchPhrase)
    }

    suspend fun getUserInfo(userId: Int): Response<UserDetails> {
        return RetrofitInstance.myApi.getUserInfo(userId)
    }

    suspend fun getUserTopTags(userId: Int): Response<TopTagList> {
        return RetrofitInstance.myApi.getUserTopTags(userId)
    }
}