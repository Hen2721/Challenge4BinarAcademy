package com.example.challenge4.core.domain.model

import android.os.Parcel
import android.os.Parcelable
import java.io.File
data class Book(
    val bookId: Int,
    val bookName: String,
    val bookJenis: String,
    val bookPenerbit: String,
    val bookPenulis: String,
    val bookTahunTerbit: String,
    val bookJumlahHalaman: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bookId)
        parcel.writeString(bookName)
        parcel.writeString(bookJenis)
        parcel.writeString(bookPenerbit)
        parcel.writeString(bookPenulis)
        parcel.writeString(bookTahunTerbit)
        parcel.writeString(bookJumlahHalaman)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}
