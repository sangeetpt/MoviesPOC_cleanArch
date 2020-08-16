package com.test.moviesdemo.features.movies

import android.os.Parcel
import com.test.moviesdemo.core.platform.KParcelable
import com.test.moviesdemo.core.platform.parcelableCreator

data class MovieView(val title: String, val image: String, val releaseYear:Int) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::MovieView)
    }

    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readString()!!,parcel.readInt())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {

            writeString(title)
            writeString(image)
            writeInt(releaseYear)
        }
    }
}
