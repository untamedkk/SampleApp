package com.test.krishna.models

object Model {
    data class Delivery(val id: Int, val description: String, val imageUrl: String, val location: Location)
    data class Location(val lat: Float, val lng: Float, val address: String)
}