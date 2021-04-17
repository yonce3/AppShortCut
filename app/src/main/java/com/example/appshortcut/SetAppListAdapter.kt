package com.example.appshortcut

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appshortcut.data.SetAppInfo

class SetAppListAdapter(private val setAppList: List<SetAppInfo>)
    : RecyclerView.Adapter<SetAppListAdapter.SetAppViewHolder>() {

    inner class SetAppViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //val appIcon = view.findViewById<ImageView>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetAppViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SetAppViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}