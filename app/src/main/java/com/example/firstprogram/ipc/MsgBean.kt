package com.example.firstprogram.ipc

import android.os.Parcel
import android.os.Parcelable

class MsgBean : Parcelable {
    var message = ""

    constructor(parcel: Parcel) : this() {
        message = parcel.readString().toString()
    }

    constructor(message: String) {
        this.message = message
    }

    constructor() {}

    override fun toString(): String {
        return message
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MsgBean> {
        override fun createFromParcel(parcel: Parcel): MsgBean {
            return MsgBean(parcel)
        }

        override fun newArray(size: Int): Array<MsgBean?> {
            return arrayOfNulls(size)
        }
    }
}