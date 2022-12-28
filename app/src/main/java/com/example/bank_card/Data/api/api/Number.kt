package com.example.bank_card.Data.api.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Number(
    @SerializedName("length")
    var length: Int,
    @SerializedName("luhn")
    var luhn: Boolean
): Serializable
