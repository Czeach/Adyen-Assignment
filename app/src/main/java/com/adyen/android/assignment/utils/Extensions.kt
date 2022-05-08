package com.adyen.android.assignment.utils

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun View.hideView(onlyInvisible: Boolean = false) {
    this.visibility = if (onlyInvisible) View.INVISIBLE else View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun Fragment.launchFragment(direction: NavDirections) = try {
    findNavController().navigate(direction)
} catch (e: Exception) {
    Log.e("NAVIGATION_ERROR", e.toString())
}

fun Context.showErrorDialog(message: String, listener: DialogInterface.OnClickListener? = null) {
    MaterialAlertDialogBuilder(this)
        .setMessage(message)
        .setPositiveButton("OK", listener)
        .show()
}