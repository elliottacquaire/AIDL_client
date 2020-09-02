package com.example.firstprogram.ipc

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class Book() : Parcelable {
    var id: Int = 0

    var name: String = ""

    var price: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString().toString()
        price = parcel.readInt()
    }

    /* constructor(source: Parcel) : this(
     )

     override fun describeContents() = 0

     override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

     companion object {
         override fun createFromParcel(parcel: Parcel): Book {
             return Book(parcel)
         }

         override fun newArray(size: Int): Array<Book?> {
             return arrayOfNulls(size)
         }

         @JvmField
         val CREATOR: Parcelable.Creator<Book> = object : Parcelable.Creator<Book> {
             override fun createFromParcel(source: Parcel): Book = Book(source)
             override fun newArray(size: Int): Array<Book?> = arrayOfNulls(size)
         }
     }*/
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(price)
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