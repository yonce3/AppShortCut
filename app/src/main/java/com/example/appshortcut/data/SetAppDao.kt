package com.example.appshortcut.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SetAppDao {
    @Query("SELECT * FROM setAppInfo")
    fun getSetAppAll(): List<SetAppInfo>

    @Insert
    fun insertAll(vararg setApps: SetAppInfo)
}