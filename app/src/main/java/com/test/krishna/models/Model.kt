package com.test.krishna.models

import android.os.Parcel
import android.os.Parcelable

object Model {
    data class Delivery(val id: Int, val description: String, val imageUrl: String, val location: Location) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readParcelable(Location::class.java.classLoader))


        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(description)
            parcel.writeString(imageUrl)
            parcel.writeParcelable(location, flags)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Delivery> {
            override fun createFromParcel(parcel: Parcel): Delivery {
                return Delivery(parcel)
            }

            override fun newArray(size: Int): Array<Delivery?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Location(val lat: Double, val lng: Double, val address: String) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readDouble(),
                parcel.readDouble(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeDouble(lat)
            parcel.writeDouble(lng)
            parcel.writeString(address)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Location> {
            override fun createFromParcel(parcel: Parcel): Location {
                return Location(parcel)
            }

            override fun newArray(size: Int): Array<Location?> {
                return arrayOfNulls(size)
            }
        }
    }
}