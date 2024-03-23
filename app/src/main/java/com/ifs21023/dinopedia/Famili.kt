package com.ifs21023.dinopedia
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Famili(
    var name: String,
    var icon: Int,
    var description: String,
    var period: String,
    var character: String,
    var habit: String,
    var perilaku: String,
    var klasifikasi: String,
    var mulai : Int,
    var selesai : Int,
): Parcelable


