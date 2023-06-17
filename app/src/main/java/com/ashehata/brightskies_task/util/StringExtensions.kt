package com.ashehata.brightskies_task.util


fun String?.doIf(call: (String) -> Unit) {
    if (this.isNullOrEmpty().not())
        call(this ?: "")
}

fun List<String?>?.doIf(call: (List<String?>) -> Unit) {
    if (this.isNullOrEmpty().not())
        call(this ?: emptyList())
}