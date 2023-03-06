package com.sngular.rgarciavital.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sngular.rgarciavital.extensions.writeTypedObjectCompat

@Entity(tableName = "review")
class Review() : Parcelable {

   /* "author": "Andres Gomez",
    "author_details": {
        "name": "Andres Gomez",
        "username": "tanty",
        "avatar_path": "/https://www.gravatar.com/avatar/03a53cd53b8254fe582d4fe1acc26c4e.jpg",
        "rating": 7.0
    },
    "content": "Interesting film with an exceptional cast, fantastic performances and characterizations. The story, though, is a bit difficult to follow and, in the end, seems to not have a real point.",
    "created_at": "2013-05-13T15:40:41.935Z",
    "id": "51910979760ee320eb020fc2",
    "updated_at": "2021-06-23T15:57:21.374Z",
    "url": "https://www.themoviedb.org/review/51910979760ee320eb020fc2"*/
    @PrimaryKey
    @SerializedName("id")
    var id: String? = null

    @SerializedName("author")
    var author: String? = null

    @SerializedName("author_details")
    var author_details: Author? = null

    @SerializedName("content")
    var content: String? = null

    @SerializedName("created_at")
    var created_at: String? = null

    var image: String? = null


    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        author = parcel.readString()
       // author_details = parcel.create(Author.CREATOR);
        author_details = parcel.readValue(Double::class.java.classLoader) as? Author
        content = parcel.readString()
        created_at = parcel.readString()

    }


    override fun writeToParcel(parser: Parcel, flags: Int) {
        parser.writeString(id)
        parser.writeString(author)
        parser.writeTypedObjectCompat(author_details,flags)
        parser.writeString(content)
        parser.writeString(created_at)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }

    }



}