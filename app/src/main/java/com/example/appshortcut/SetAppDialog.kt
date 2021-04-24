package com.example.appshortcut

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.appshortcut.view.MainActivity

// TODO: Not to use constructor
class SetAppDialog(private val app: AppInfo): DialogFragment() {
    interface OnClickListener {
        fun onClickOk(app: AppInfo)
    }
    private lateinit var listener: OnClickListener

    override fun onAttach(context: Context) {
        this.listener = activity as MainActivity
        super.onAttach(context)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_set_app, null)
            view.findViewById<ImageView>(R.id.app_icon).setImageDrawable(app.appIcon)
            view.findViewById<TextView>(R.id.app_label).text = app.appLabel

            builder.setMessage(R.string.set_app_dialog_message)
                    .setView(view)
                    .setPositiveButton(R.string.set_app_dialog_yes) { _, _ ->
                        listener.onClickOk(app)
                    }
                    .setNegativeButton(R.string.set_app_dialog_no) { _, _ -> }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}