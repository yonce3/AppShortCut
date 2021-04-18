package com.example.appshortcut

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppListAdapter(private val appList: List<AppInfo>): RecyclerView.Adapter<AppListAdapter.AppListViewHolder>() {
    lateinit var listener: OnItemClickListener

    inner class AppListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val appIcon: ImageView = view.findViewById(R.id.app_icon)
        val appLabel: TextView = view.findViewById(R.id.app_label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.app_list_item, parent, false)
        return AppListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppListViewHolder, position: Int) {
        holder.appLabel.text = appList[position].appLabel
        holder.appIcon.setImageDrawable(appList[position].appIcon)

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(it, position, "hello")
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int, clickedText: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun getItemCount(): Int = appList.size
}