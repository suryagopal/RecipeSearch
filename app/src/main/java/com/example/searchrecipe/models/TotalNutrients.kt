package com.example.searchrecipe.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TotalNutrients(
    @SerializedName("ENERC_KCAL")
    val ENERC_KCAL: ENERCKCAL,
    @SerializedName("PROCNT")
    val PROCNT: PROCNT

) : Parcelable