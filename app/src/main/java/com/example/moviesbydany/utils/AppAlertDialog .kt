package com.example.moviesbydany.utils

import android.app.AlertDialog
import android.content.Context

class AppAlertDialog(val context: Context) {
    fun buildDialog(message: String, onRetry: () -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error!!")
        builder.setMessage(message)
        builder.setPositiveButton("Retry") { dialog, which ->
            onRetry.invoke()
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}