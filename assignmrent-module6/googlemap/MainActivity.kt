package com.example.googlemap

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mLocation: Location
    private var mLatitude: Double = 0.0
    private var mLongitude: Double = 0.0
    private lateinit var searchView: SearchView

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.title = "Google Map"
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.search)

        /**
         * Obtaining SupportMapFragment
         * Getting notified when map is ready to be used.
         */
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fgmap) as SupportMapFragment

        /**
         * Requesting ACCESS_FINE_LOCATION & ACCESS_COARSE_LOCATION
         */
        requestPermission()

        /**
         * Adding onQueryTextListener for searchView
         */
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Getting user input in searchView
                val location = searchView.query.toString()
                // Creating list to store addresses
                var addressList: List<Address>? = null
                // Checking searchView query is empty or not
                // Creating and initializing Geocoder
                val geocoder = Geocoder(this@MainActivity)
                // In below code getting location names and adding them in addressList
                try {
                    addressList = geocoder.getFromLocationName(location, 1)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                // Now getting location from addressList at position 1
                val address = addressList?.get(0)
                // Creating LatLng to store our location's latitude and longitude
                val latLng = LatLng(address!!.latitude, address.longitude)
                // Adding a marker on map
                mMap.addMarker(MarkerOptions().position(latLng).title(location))
                // Animate camera to above position
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        val locationManager =
            this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (!locationManager.isProviderEnabled(GPS_PROVIDER)) {
            Toast.makeText(applicationContext, "GPS off", Toast.LENGTH_SHORT).show()
        } else if (!locationManager.isProviderEnabled(NETWORK_PROVIDER)) {
            Toast.makeText(applicationContext, "Internet is off", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Fetching current location", Toast.LENGTH_SHORT)
                .show()
        }

        val locationListener = LocationListener {
            mLatitude = mLocation.latitude
            mLongitude = mLocation.longitude
        }

        if (locationManager.isProviderEnabled(NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(
                NETWORK_PROVIDER,
                0,
                0.0f, locationListener
            )
        }

        try {
            mLocation = locationManager.getLastKnownLocation(NETWORK_PROVIDER)!!
            mLatitude = mLocation.latitude
            mLongitude = mLocation.longitude
            Toast.makeText(
                applicationContext,
                "$mLatitude $mLongitude",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: NullPointerException) {
            Toast.makeText(
                applicationContext,
                "Could not fetch Current Location.",
                Toast.LENGTH_SHORT
            ).show()
            Toast.makeText(
                applicationContext,
                "Please restart an app with Internet and Location on",
                Toast.LENGTH_SHORT
            ).show()
        }

        /**
         * Calling map fragment to update
         */
        mapFragment.getMapAsync(this)
    }

    private fun requestPermission() {
        if (
            ActivityCompat
                .checkSelfPermission(this, ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
            && ActivityCompat
                .checkSelfPermission(this, ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 0
            )
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        val currentLocation = LatLng(mLatitude, mLongitude)
        mMap.addMarker(MarkerOptions().position(currentLocation).title("I am here!"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
    }

    /*
    Creating Options menu for changing style of Google Maps
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /*
    Adding code for different Google Maps Modes
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.o1 -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }

            R.id.o2 -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }

            R.id.o3 -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }

            R.id.o4 -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
        }
        return super.onOptionsItemSelected(item)
    }
}