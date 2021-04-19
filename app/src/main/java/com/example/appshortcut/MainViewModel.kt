package com.example.appshortcut

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.appshortcut.data.AppRepository
import com.example.appshortcut.data.SetAppDatabase
import com.example.appshortcut.data.SetAppInfo
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var repository: AppRepository = AppRepository(Room.databaseBuilder(application, SetAppDatabase::class.java, "appDatabase").build())
    // var setAppList = LiveData<SetAppInfo>()

    fun saveApp(setAppInfo: SetAppInfo) {
        viewModelScope.launch {
            try {
                repository.setApp(setAppInfo)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}