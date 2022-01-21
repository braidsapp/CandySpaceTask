package com.example.candyspace

import com.google.gson.annotations.SerializedName

data class ListOfUsers (
    val items: ArrayList<User>
)

data class User (

    @SerializedName("user_id")
    var id: Int,

    @SerializedName("display_name")
    var name: String,
)
