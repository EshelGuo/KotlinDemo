package com.example.kotlin.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin.base.App
import com.example.kotlin.database.dao.UserTrackDao
import com.example.kotlin.database.entity.Event

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/6 18:18
 * <br>desc: TODO
 */
@Database(entities = arrayOf(Event::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val instance:AppDatabase by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
                Room.databaseBuilder(App.instance, AppDatabase::class.java, "KotlinDemoDatabase")
                    .build()
        }
    }
    abstract fun userTrack():UserTrackDao
}