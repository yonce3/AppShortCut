package com.example.appshortcut.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SetAppInfo::class], version = 1)
abstract class SetAppDatabase: RoomDatabase() {
    abstract fun getSetAppDao(): SetAppDao
}