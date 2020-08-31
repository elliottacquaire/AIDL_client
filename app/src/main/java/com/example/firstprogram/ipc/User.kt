package com.example.firstprogram.ipc

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class User(val name: String, val age: Int) : Parcelable
