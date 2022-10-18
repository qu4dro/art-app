package dev.orlovvv.art.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdvertisingUi(
    val sponsor_name: String,
    val description: String,
    val url: String,
): Parcelable