package com.example.appshortcut

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class SetAppListAdapter(private val setAppList: List<Drawable>)
    : RecyclerView.Adapter<SetAppListAdapter.SetAppViewHolder>() {

    inner class SetAppViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val appIcon: ImageView = view.findViewById(R.id.app_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetAppViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.set_app_list_item, parent, false)

        return SetAppViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetAppViewHolder, position: Int) {
        holder.appIcon.setImageDrawable(setAppList[position])
    }

    override fun getItemCount(): Int = setAppList.size
}