package com.example.bank_card.Data.api.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Bank(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("city")
    var city: String
): Serializable
