package com.sngular.rgarciavital.data.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName


class ImageResult() : Parcelable {


    @SerializedName("backdrops")
    var backdrops: List<ImageMovie>? = null

    constructor(parcel: Parcel) : this() {
        backdrops = parcel.createTypedArrayList(ImageMovie.CREATOR);
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(backdrops)
    }

    companion object CREATOR : Parcelable.Creator<ImageResult> {
        override fun createFromParcel(parcel: Parcel): ImageResult {
            return ImageResult(parcel)
        }

        override fun newArray(size: Int): Array<ImageResult?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }


}
