package com.example.navfragment

import android.content.Context
import android.widget.Toast

object ToastMaker {
    private var toast: Toast? = null

    internal fun makeToast(context: Context, message: String) {
        toast?.cancel()
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast?.show()
    }
}