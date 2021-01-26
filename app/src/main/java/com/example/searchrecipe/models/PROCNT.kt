package com.example.searchrecipe.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PROCNT(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable