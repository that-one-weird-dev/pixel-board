package com.zeropixel.pixelboard.utils

class AlertDialogOptions(
    val message: String,
    val onCancel: () -> Unit = {},
    val onConfirm: () -> Unit
)