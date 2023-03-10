package com.example.bank_card.Data.api.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info")
data class BinCardInfo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "bin")
    val bin: String,
    @ColumnInfo(name = "scheme")
    val scheme: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "country name")
    val countryName: String,
    @Embedded
    val bankInfo: BankInfo
)
