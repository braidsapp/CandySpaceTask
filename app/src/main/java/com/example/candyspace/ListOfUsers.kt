package com.example.candyspace

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

data class ListOfUsers (
    val items: ArrayList<User>
)


data class User (

    @SerializedName("account_id")
    var id: Int,

    @SerializedName("display_name")
    var name: String,

    var reputation: Int,

    @SerializedName("collectives")
    var tags: ArrayList<Any>,

    @SerializedName("badge_counts")
    var badges: BadgeCounts,

    var location: String,

    @SerializedName("creation_date")
    var creationDateUnix: Int,

    @SerializedName("profile_image")
    var avatar: String
)

data class BadgeCounts (
    var bronze: Int,
    var silver: Int,
    var gold: Int
)

data class Collectives(
    var collectives: ArrayList<Any>
)


data class Collective(
    var tags: ArrayList<String>
)
