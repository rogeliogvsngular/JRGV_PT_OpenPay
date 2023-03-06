package com.sngular.rgarciavital.extensions

import android.os.Parcel
import android.os.Parcelable

fun <T : Parcelable> Parcel.writeTypedObjectCompat(value: T?, flags: Int) =
    writeNullable(value) { it.writeToParcel(this, flags) }

inline fun <T> Parcel.writeNullable(value: T?, writer: (T) -> Unit) {
    if (value != null) {
        writeInt(1)
        writer(value)
    } else {
        writeInt(0)
    }
}