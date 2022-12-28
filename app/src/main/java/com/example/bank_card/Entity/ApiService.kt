package com.example.bank_card.Entity

import com.example.bank_card.Data.api.api.BankCard
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/{number}")
    fun getInfo(@Path("number") number: String) : Call<BankCard>
}