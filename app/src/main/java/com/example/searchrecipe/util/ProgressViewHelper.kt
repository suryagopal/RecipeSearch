package com.example.searchrecipe.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.searchrecipe.databinding.ProgressViewHelperBinding

/**
 *  Dialog to be shown during Service Request calls
 */

class ProgressViewHelper {

    companion object {
        fun progressDialog(context: Context): Dialog {
            val dialog = Dialog(context)
            val binding = ProgressViewHelperBinding.inflate(LayoutInflater.from(context))
            dialog.setContentView(binding.root)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )
            return dialog
        }
    }
}