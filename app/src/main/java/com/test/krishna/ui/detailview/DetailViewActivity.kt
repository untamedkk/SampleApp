package com.test.krishna.ui.detailview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.test.krishna.R
import com.test.krishna.models.Model
import kotlinx.android.synthetic.main.item_delivery_layout.*

private const val DELIVERY_INTENT_KEY: String = "delivery_intent_key"

class DetailViewActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        fun open(context: Context, delivery: Model.Delivery) {
            val intent = Intent(context, DetailViewActivity::class.java)
            intent.putExtra(DELIVERY_INTENT_KEY, delivery)
            context.startActivity(intent)
        }
    }

    private lateinit var delivery: Model.Delivery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        delivery = intent.getParcelableExtra(DELIVERY_INTENT_KEY)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        setDeliveryDetails()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val deliveryLocation = LatLng(delivery.location.lat, delivery.location.lng)
        googleMap.addMarker(MarkerOptions().position(deliveryLocation).title(delivery.description))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(deliveryLocation))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(11f))
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    private fun setDeliveryDetails() {
        item_delivery_description_tv.text = delivery.description
        item_delivery_location_tv.text = delivery.location.address
        Glide.with(this).load(delivery.imageUrl)
                .apply(glideRequestOption)
                .into(item_delivery_icon_iv)
    }

    private val glideRequestOption: RequestOptions by lazy {
        RequestOptions()
                .transform(CenterCrop())
                .override(150, 150)
    }

}