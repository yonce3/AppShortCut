package com.example.appshortcut

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.appshortcut.view.MainActivity


class SetAppDialog: DialogFragment() {
    interface OnClickListener {
        fun onClickOk()
    }
    lateinit var listener: OnClickListener

    override fun onAttach(context: Context) {
        this.listener = activity as MainActivity
        super.onAttach(context)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.set_app_dialog_message)
                .setPositiveButton(R.string.set_app_dialog_yes,
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onClickOk()
                    })
                .setNegativeButton(R.string.set_app_dialog_no,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}