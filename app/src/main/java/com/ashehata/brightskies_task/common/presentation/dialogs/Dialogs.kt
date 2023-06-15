package com.ashehata.brightskies_task.common.presentation.dialogs

import android.content.Context
import com.ashehata.brightskies_task.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun showMaterialDialog(
    context: Context,
    title: String,
    content: String,
    positiveMessage: String?,
    negativeMessage: String?,
    positive: (() -> Unit)?,
    negative: (() -> Unit)?,
    isCancellable: Boolean = true
) {
    MaterialAlertDialogBuilder(context, R.style.Theme_Material_Dialog)
        .setTitle(title)
        .setCancelable(isCancellable)
        .setMessage(content)
        .setPositiveButton(positiveMessage) { _, _ ->
            positive?.invoke()
        }
        .setNegativeButton(negativeMessage) { _, _ ->
            negative?.invoke()
        }
        .show()
}
