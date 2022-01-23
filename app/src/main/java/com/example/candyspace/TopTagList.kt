package com.example.candyspace

import com.google.gson.annotations.SerializedName

data class TopTagList (
    val items: ArrayList<Tag>
)

data class Tag (
    @SerializedName("tag_name")
    val tag_name: String,
)
