package com.example.appshortcut

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class SetAppListAdapter(private var setAppIconList: List<AppInfo>)
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
        val bitmap = getBitmapFromDrawable(setAppIconList[position].appIcon)
        holder.appIcon.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int = setAppIconList.size

    fun setData(appIconList: List<AppInfo>) {
        setAppIconList = appIconList
        notifyDataSetChanged()
    }

    private fun getBitmapFromDrawable(drawable: Drawable): Bitmap {
        val bmp = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bmp
    }
}