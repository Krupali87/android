package com.example.wifionoff


import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{
    lateinit var wifiManager: WifiManager
    lateinit var toggleButton: ToggleButton
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleButton  = findViewById(R.id.toggleButton)
        textView = findViewById(R.id.textView)

        toggleButton.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                textView.text = "WiFi is ON"
                val wifi = getSystemService(WIFI_SERVICE) as WifiManager
                wifi.isWifiEnabled = true
            } else {
                textView.text = "WiFi is OFF"
                val wifi = getSystemService(WIFI_SERVICE) as WifiManager
                wifi.isWifiEnabled = false
            }
        }

        if (toggleButton.isChecked) {
            textView.text = "WiFi is ON"
            val wifi = getSystemService(WIFI_SERVICE) as WifiManager
            wifi.isWifiEnabled = true
        } else {
            textView.text = "WiFi is OFF"
            val wifi = getSystemService(WIFI_SERVICE) as WifiManager
            wifi.isWifiEnabled = false
        }


    }

}