package com.sngular.rgarciavital.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(

    var id: String? = null,

    var longitude: String? = null,

    var latitude: String? = null,

    var name: String? = null

): Parcelable