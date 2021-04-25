package com.example.appshortcut.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appshortcut.*

class MainActivity : AppCompatActivity(), SetAppDialog.OnClickListener {
    private val viewModel by lazy { ViewModelProvider.AndroidViewModelFactory(application).create(MainViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set App List
        val setListLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        val setListAdapter = SetAppListAdapter(listOf())
        findViewById<RecyclerView>(R.id.set_app_list).apply {
            this.layoutManager = setListLayoutManager
            this.adapter = setListAdapter
        }

        viewModel.setAppList.observe(this, { list ->
            setListAdapter.setData(getApplicationByAppLabel(list.map { it.appLabel }))
        })

        // Installed App List
        val layoutManager = LinearLayoutManager(this)
        val listAdapter = AppListAdapter(getApplicationList())
        listAdapter.setOnItemClickListener(object: AppListAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int, clickedText: String) {
                val app = getApplicationList()[position]
                SetAppDialog(app).show(supportFragmentManager, "setAppDialog")
            }
        })
        findViewById<RecyclerView>(R.id.app_list).apply {
            this.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            this.layoutManager = layoutManager
            this.adapter = listAdapter
        }

        val builder = NotificationCompat.Builder(this, 2.toString())
                .setContentTitle("Hello")
                .setContentText("Hell")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.appbar_scrolling_view_behavior)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(4.toString(), name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(4, builder.build())
        }
    }

    private fun getApplicationList(): List<AppInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val appInfoList = packageManager.queryIntentActivities(intent, 0)
        return appInfoList.map {
            AppInfo(
                it.loadLabel(packageManager).toString(),
                it.loadIcon(packageManager)
            )
        }
    }

    private fun getApplicationByAppLabel(appLabelList: List<String>): List<AppInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val appInfoList = packageManager.queryIntentActivities(intent, 0).filter { appLabelList.contains(it.loadLabel(packageManager).toString()) }
        return appInfoList.map {
            AppInfo(it.loadLabel(packageManager).toString(), it.loadIcon(packageManager))}
    }

    override fun onClickOk(app: AppInfo) {
        viewModel.saveApp(app)
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
    }
}