package com.example.firstprogram.ipc


import android.os.Parcel
import android.os.Parcelable

class StudentBean() : Parcelable {
    var name: String = ""
    var grade: Int = 0

    constructor(name: String, grade: Int) : this() {
        this.name = name
        this.grade = grade
    }

    constructor(parcel: Parcel) : this() {
        name = parcel.readString().toString()
        grade = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(grade)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun readFromParcel(reply: Parcel) {
        name = reply.readString().toString()
        grade = reply.readInt()
    }

    companion object CREATOR : Parcelable.Creator<StudentBean> {
        override fun createFromParcel(parcel: Parcel): StudentBean {
            return StudentBean(parcel)
        }

        override fun newArray(size: Int): Array<StudentBean?> {
            return arrayOfNulls(size)
        }
    }

}