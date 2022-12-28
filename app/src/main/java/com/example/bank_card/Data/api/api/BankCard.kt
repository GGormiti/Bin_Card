package com.example.bank_card.Data.api.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BankCard(
    @SerializedName("number")
    var number: Number,

    @SerializedName("scheme")
    var scheme: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("brand")
    var brand: String,

    @SerializedName("prepaid")
    var prepaid: Boolean,

    @SerializedName("country")
    var country: Country,

    @SerializedName("bank")
    var bank: Bank
): Serializable
