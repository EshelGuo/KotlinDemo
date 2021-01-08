package com.example.kotlin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin.database.entity.Event

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/6 18:14
 * <br>desc: TODO
 */
@Dao
interface UserTrackDao {
    @Insert
    fun insert(vararg event: Event)
    @Query("SELECT * from Event")
    fun queryAll():List<Event>
}