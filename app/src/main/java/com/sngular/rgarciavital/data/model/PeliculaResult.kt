package com.sngular.rgarciavital.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PeliculaResult() : Parcelable {
    @SerializedName("page")
    var page = 0

    @SerializedName("results")
    var peliculas: List<Pelicula>? = null

    constructor(parcel: Parcel) : this() {
        page = parcel.readInt()
        peliculas = parcel.createTypedArrayList(Pelicula.CREATOR);
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeTypedList(peliculas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PeliculaResult> {
        override fun createFromParcel(parcel: Parcel): PeliculaResult {
            return PeliculaResult(parcel)
        }

        override fun newArray(size: Int): Array<PeliculaResult?> {
            return arrayOfNulls(size)
        }
    }


}