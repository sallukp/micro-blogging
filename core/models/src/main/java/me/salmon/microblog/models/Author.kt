package me.salmon.microblog.models

import android.os.Parcel
import android.os.Parcelable

data class Author(val id: Int,
                  val name: String?,
                  val userName: String?,
                  val email: String?,
                  val avatarUrl: String?,
                  val lat: Float,
                  val long: Float): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readFloat()
    )

    override fun equals(other: Any?): Boolean {
        (other as? Author)?.let {
            return it.id == id
        }
        return super.equals(other)
    }

    fun getFirstLetters(): String {
        var firstLetters = ""
        name?.let {
            it.split(" ", limit = 2).map { part ->
                firstLetters += if (part.isNotEmpty()) part.subSequence(0, 1) else ""
            }
        }
        return firstLetters
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(userName)
        parcel.writeString(email)
        parcel.writeString(avatarUrl)
        parcel.writeFloat(lat)
        parcel.writeFloat(long)
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