package com.example.appshortcut.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class AppRepository(private val db: SetAppDatabase) {

    suspend fun setApp(setAppInfo: SetAppInfo) {
        try {
            withContext(Dispatchers.IO) {
                db.getSetAppDao().insertAll(setAppInfo)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun loadAppList(): LiveData<List<SetAppInfo>> {
        var result: LiveData<List<SetAppInfo>> = db.getSetAppDao().loadAppInfo()
//        try {
            //withContext(Dispatchers.IO) {
                result = db.getSetAppDao().loadAppInfo()
           // }
        //} catch (e: IOException) {
           // e.printStackTrace()
        //}

        return result
    }
}