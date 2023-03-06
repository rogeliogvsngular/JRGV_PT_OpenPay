package com.sngular.rgarciavital.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 *   {
"adult": false,
"backdrop_path": "/hegMHNsxYGlGgVgaGz9FqxPqImr.jpg",
"genre_ids": [
27,
9648,
53
],
"id": 631842,
"original_language": "en",
"original_title": "Knock at the Cabin",
"overview": "Mientras está de vacaciones en una cabaña remota, una niña y sus padres son tomados como rehenes por cuatro extraños armados que exigen que la familia tome una decisión impensable para evitar el apocalipsis. Con acceso limitado al mundo exterior, la familia debe decidir en qué cree antes de que todo se pierda.",
"popularity": 4112.616,
"poster_path": "/rPcpqODOEXjVAf8MEVg2kOQALt0.jpg",
"release_date": "2023-02-01",
"title": "Llaman a la puerta",
"video": false,
"vote_average": 6.5,
"vote_count": 670
}
 *
 *
 *
 */
@Entity(tableName = "pelicula")
class Pelicula() : Parcelable {

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null


    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("popularity")
    var popularity: Double? = null

    @SerializedName("vote_count")
    var voteCount: Int? = null

    @SerializedName("vote_average")
    var voteAverage: Double? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        posterPath = parcel.readString()
        title = parcel.readString()
        originalTitle = parcel.readString()
        overview = parcel.readString()
        releaseDate = parcel.readString()
        popularity = parcel.readValue(Double::class.java.classLoader) as? Double
        voteCount = parcel.readValue(Int::class.java.classLoader) as? Int
        voteAverage = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parser: Parcel, flags: Int) {
        parser.writeValue(id)
        parser.writeString(posterPath)
        parser.writeString(title)
        parser.writeString(originalTitle)
        parser.writeString(overview)
        parser.writeString(releaseDate)
        parser.writeValue(popularity)
        parser.writeValue(voteCount)
        parser.writeValue(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<Pelicula> {
        override fun createFromParcel(parcel: Parcel): Pelicula {
            return Pelicula(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula?> {
            return arrayOfNulls(size)
        }
    }

}