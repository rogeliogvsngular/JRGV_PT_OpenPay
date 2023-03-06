package com.sngular.rgarciavital.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "imagemovie")
class ImageMovie() : Parcelable {

    @PrimaryKey
    @SerializedName("file_path")
    var file_path: String? = null

    constructor(parcel: Parcel) : this() {
        file_path =  parcel.readString()
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(file_path)
    }

    companion object CREATOR : Parcelable.Creator<ImageMovie> {
        override fun createFromParcel(parcel: Parcel): ImageMovie {
            return ImageMovie(parcel)
        }

        override fun newArray(size: Int): Array<ImageMovie?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }


}