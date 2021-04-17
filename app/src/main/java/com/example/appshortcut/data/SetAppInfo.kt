package com.example.appshortcut.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SetAppInfo (
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  @ColumnInfo(name="app_label")val appLabel: String
)