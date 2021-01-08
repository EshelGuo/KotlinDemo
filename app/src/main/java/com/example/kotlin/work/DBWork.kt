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
