package com.example.candyspace

import retrofit2.Response

class Repository {
    suspend fun getUsers(): Response<ListOfUsers> {
        return RetrofitInstance.myApi.getUsers()
    }

    suspend fun getUsers(searchPhrase: String): Response<ListOfUsers> {
        return RetrofitInstance.myApi.getUsers(searchPhrase)
    }
}