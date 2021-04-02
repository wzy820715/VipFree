package com.demo.vipfree.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WebsiteInfo(
    val name: String,
    val icon: String,
    val url: String,
    val prefix: MutableList<String>) : Parcelable