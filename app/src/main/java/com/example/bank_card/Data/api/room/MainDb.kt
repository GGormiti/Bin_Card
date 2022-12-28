package com.example.bank_card.Data.api.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BinCardInfo::class], version = 1)
abstract class MainDb : RoomDatabase() {

    abstract fun cardInfoDao(): BinCardInfoDao

    companion object {
        fun getDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "main.db"
            ).build()
        }
    }

}