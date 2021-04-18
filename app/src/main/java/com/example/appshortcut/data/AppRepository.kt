package com.example.appshortcut.data

import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class AppRepository(private val db: SetAppDatabase) {

    suspend fun setApp() {
        try {
            withContext(Dispatchers.IO) {
                db.getSetAppDao().insertAll()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}