package com.sngular.rgarciavital.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ReviewResult() : Parcelable {

    @SerializedName("page")
    var page = 0

    @SerializedName("results")
    var reviews: List<Review>? = null

    constructor(parcel: Parcel) : this() {
        page = parcel.readInt()
        reviews = parcel.createTypedArrayList(Review.CREATOR);
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeTypedList(reviews)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewResult> {
        override fun createFromParcel(parcel: Parcel): ReviewResult {
            return ReviewResult(parcel)
        }

        override fun newArray(size: Int): Array<ReviewResult?> {
            return arrayOfNulls(size)
        }
    }


}