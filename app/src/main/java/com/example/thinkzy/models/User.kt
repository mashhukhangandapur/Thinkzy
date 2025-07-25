package com.example.thinkzy.models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelCreator")
data class User(
    val id : String = "",
    val name : String = "",
    val email : String = "",
    val image : String = "",
    val fcmToken : String = "",
    val MobileNumber : Long = 0
) : Parcelable {
    constructor(parcel : Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong()
    )
    override fun describeContents() = 0



    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest){
        writeString(id)
        writeString(name)
        writeString(email)
        writeString(image)
        writeString(fcmToken)
        writeLong(MobileNumber)
    }
}