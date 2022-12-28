package com.example.bank_card.Data.api.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(
    @SerializedName("numeric")
    var numeric: String,
    @SerializedName("alpha2")
    var alpha2: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("emoji")
    var emoji: String,
    @SerializedName("currency")
    var currency: String,
    @SerializedName("latitude")
    var latitude: Int,
    @SerializedName("longitude")
    var longitude: Int
): Serializable
