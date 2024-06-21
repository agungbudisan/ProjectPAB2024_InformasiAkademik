package com.pab2024klsa.informasiakademik.ui.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val img: Int,
    val name: String,
    val nim: String
) : Parcelable