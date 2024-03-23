package com.ifs21023.dinopedia
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino(
    var name : String,
    var icon : Int,
    var deskripsi : String,
    var kelompok: String,
    var habitat: String,
): Parcelable