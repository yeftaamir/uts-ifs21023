package com.ifs21023.dinopedia
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dinosaurus(
    var name: String,
    var icon: Int,
    var description: String,
    var period: String,
    var character: String,
    var habit: String,
    var perilaku: String,
    var klasifikasi: String,
    var gambar1: Int,
    var gambar2: Int,
    var gambar3: Int,
    var gambar4: Int,
    var gambar5: Int,
    var namadino1: String,
    var namadino2: String,
    var namadino3: String,
    var namadino4: String,
    var namadino5: String,
    var descDino1: String,
    var descDino2: String,
    var descDino3: String,
    var descDino4: String,
    var descDino5: String
): Parcelable


