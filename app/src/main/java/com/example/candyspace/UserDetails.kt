package com.example.candyspace

import com.google.gson.annotations.SerializedName

data class UserDetails (
    val items: ArrayList<UserInfo>
)

data class UserInfo (

    @SerializedName("profile_image")
    val avatar: String,

    @SerializedName("display_name")
    val name: String,

    //@SerializedName("reputation")
    val reputation: Int,

    @SerializedName("badge_counts")
    val badgeCounts: BadgeCounts,

    //@SerializedName("profile_image")
    val location: String,

    @SerializedName("creation_date")
    val userSinceUNIX: Int
)

data class BadgeCounts(
    val bronze: Int,
    val silver: Int,
    val gold: Int
)