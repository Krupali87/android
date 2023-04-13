package com.example.webview2


import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.Button
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    lateinit var web: WebView
    lateinit var back: Button
    lateinit var forward: Button
    lateinit var chkinternet: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        web = findViewById(R.id.web)
        back = findViewById(R.id.back)
        forward = findViewById(R.id.forward)
        chkinternet = findViewById(R.id.chkinternet)

        web.getSettings().setJavaScriptEnabled(true)
        web.webViewClient = WebViewClient()


        back.setOnClickListener {

            if (web.canGoBack()) {
                web.goBack()
            }
        }
        forward.setOnClickListener {

            if (web.canGoForward()) {
                web.goForward()
            }
        }
        chkinternet.setOnClickListener {
            Toast.makeText(applicationContext, "kkkk", Toast.LENGTH_SHORT).show()
            if (checkNetwork(this)) {
                Toast.makeText(applicationContext, "Connected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Disconnected", Toast.LENGTH_SHORT).show()
            }

        }

        web.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {

                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                back.setEnabled(view!!.canGoBack())
                forward.setEnabled(view.canGoForward())

            }


        }

        web.loadUrl("https://www.google.com/")
    }

    private fun checkNetwork(context: Context): Boolean {
        val connManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connManager.activeNetwork ?: return false
            val activeNetwork = connManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val networkInfo = connManager.activeNetworkInfo ?: return false
            Toast.makeText(applicationContext, "success" + networkInfo, Toast.LENGTH_SHORT).show()
            return networkInfo.isConnected
        }
    }
}












