package com.example.appshortcut

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appshortcut.data.SetAppInfo
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {

    // var setAppList = LiveData<SetAppInfo>()

    fun saveApp() {
        viewModelScope.launch {
//            try {
//
//            }
        }
    }
}