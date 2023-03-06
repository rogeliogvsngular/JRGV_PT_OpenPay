package com.sngular.rgarciavital.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "author")
class Author()  : Parcelable {

    @PrimaryKey
    @SerializedName("username")
    var username: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("avatar_path")
    var avatar_path: String? = null

    @SerializedName("rating")
    var rating: Double? = null


    constructor(parcel: Parcel) : this() {
        username = parcel.readString()
        name = parcel.readString()
        avatar_path = parcel.readString()
        rating = parcel.readValue(Double::class.java.classLoader) as? Double

    }

    override fun writeToParcel(parser: Parcel, flags: Int) {
        parser.writeString(username)
        parser.writeString(name)
        parser.writeString(avatar_path)
        parser.writeValue(rating)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<Author> {
        override fun createFromParcel(parcel: Parcel): Author {
            return Author(parcel)
        }

        override fun newArray(size: Int): Array<Author?> {
            return arrayOfNulls(size)
        }
    }



}

