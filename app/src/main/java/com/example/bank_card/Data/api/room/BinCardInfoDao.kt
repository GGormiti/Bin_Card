package com.example.bank_card.Data.api.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BinCardInfoDao {
    @Query("SELECT * FROM info")
    fun getAll(): Flow<List<BinCardInfo>>

    @Query("DELETE FROM info")
    fun deleteAll()

    @Insert
    fun insert(cardInfo: BinCardInfo)

    @Delete
    fun delete(cardInfo: BinCardInfo)

    @Update
    fun update(cardInfo: BinCardInfo)

}