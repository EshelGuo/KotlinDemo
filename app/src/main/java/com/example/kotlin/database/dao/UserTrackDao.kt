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