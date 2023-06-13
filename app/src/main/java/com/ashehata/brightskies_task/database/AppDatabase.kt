package com.ashehata.brightskies_task.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    //abstract fun channelDao(): ChannelDao
}

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)