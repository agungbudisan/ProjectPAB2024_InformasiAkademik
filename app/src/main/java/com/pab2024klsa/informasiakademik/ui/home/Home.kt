package com.pab2024klsa.informasiakademik.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Home(
    val title: String,
    val desc: String
) : Parcelable