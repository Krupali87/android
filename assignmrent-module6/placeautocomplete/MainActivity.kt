package com.example.placeauto

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.title = "Place AutoComplete"
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val apiKey = getString(R.string.api_key)

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }

        val autocompleteSupportFragment1 =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment1) as AutocompleteSupportFragment?
        autocompleteSupportFragment1!!.setPlaceFields(
            listOf(

                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.PHONE_NUMBER,
                Place.Field.LAT_LNG,
                Place.Field.OPENING_HOURS,
                Place.Field.RATING,
                Place.Field.USER_RATINGS_TOTAL

            )
        )

        autocompleteSupportFragment1.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Toast.makeText(applicationContext, "Error $p0", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n")
            override fun onPlaceSelected(p0: Place) {
                val textView = findViewById<TextView>(R.id.tv1)

                val name = p0.name
                val address = p0.address
                val phone = p0.phoneNumber.toString()
                val latlng = p0.latLng
                val latitude = latlng?.latitude
                val longitude = latlng?.longitude

                val isOpenStatus: String = if (p0.isOpen == true) {
                    "Open"
                } else {
                    "Closed"
                }
                val rating = p0.rating
                val userRatings = p0.userRatingsTotal

                textView.text = "Name: $name \nAddress: $address \nPhone Number: $phone \n" +
                        "Latitude, Longitude: $latitude , $longitude \nIs open: $isOpenStatus \n" +
                        "Rating: $rating \nUser ratings: $userRatings"

            }


        })
    }
}