package com.example.kotlin.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/6 18:08
 */
@Entity
data class Event(
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    @ColumnInfo(name = "type")
    val type:String,
    @ColumnInfo(name = "event")
    val event:String,
    @ColumnInfo(name = "time")
    val time:Long
){
    constructor(type: String, event: String, time: Long = System.currentTimeMillis()) : this(null, type, event, time)

    override fun toString(): String {
        return "id: $id, type: $type, event: $event, time: ${formatTime(time)}"
    }

    companion object {
        val format:SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
        fun Click(event:String): Event{
            return Event("Click", event)
        }

        fun formatTime(time: Long):String{
            return format.format(Date(time))
        }
    }
}