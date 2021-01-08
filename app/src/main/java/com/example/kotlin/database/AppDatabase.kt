/*
 * Copyright (c) 2021 Eshel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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