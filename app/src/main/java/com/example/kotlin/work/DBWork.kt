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

package com.example.kotlin.work

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/6 19:05
 * <br>desc: TODO
 */
/*
class DBWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        when(inputData.getString("action")){
            "insert" -> {
                val name = inputData.getString("name")
                name?.let {
                    AppDatabase.instance.userTrack().insert(Event.Click(it))
                }
            }
            "queryAll" -> {
                val result = AppDatabase.instance.userTrack().queryAll();
                return Result.success(workDataOf(Pair()))
            }
        }
        return Result.success()
    }

    companion object {
        fun insert(name: String){
            val request = OneTimeWorkRequestBuilder<DBWork>()
                .setInputData(workDataOf(
                    Pair("name", name),
                    Pair("action", "insert")
                ))
                .build()
            WorkManager.getInstance(App.instance)
                .enqueue(request)
        }

        fun queryAll(lifecycle: LifecycleOwner): Operation{
            val request = OneTimeWorkRequestBuilder<DBWork>()
                .setInputData(workDataOf(
                    Pair("action", "queryAll")
                ))
                .build()
            val it = WorkManager.getInstance(App.instance)
                .enqueue(request);
            WorkManager.getInstance(App.instance)
                .getWorkInfoByIdLiveData(request.id)
                .observe(lifecycle, Observer {
                    it.outputData
                });
        }
    }
}*/
