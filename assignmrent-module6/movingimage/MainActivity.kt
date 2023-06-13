package com.example.movingimage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity()
{
    lateinit var imageView: ImageView
     var xDelta = 0
     var yDelta = 0


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image)

        val layoutParams = RelativeLayout.LayoutParams(800, 600)
        imageView.layoutParams = layoutParams

        imageView.setOnTouchListener(CustomTouchListener())


    }


}

private class CustomTouchListener : View.OnTouchListener {
    override fun onTouch(v: View?, event: MotionEvent?): Boolean
    {
        val x = event!!.rawX.toInt()
        val y = event.rawY.toInt()

        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val lParams = v!!.layoutParams as RelativeLayout.LayoutParams
                var xDelta = x - lParams.leftMargin
                var yDelta = y - lParams.topMargin
            }
            MotionEvent.ACTION_UP -> {


            }
            MotionEvent.ACTION_POINTER_DOWN -> {
            }
            MotionEvent.ACTION_POINTER_UP -> {
            }
            MotionEvent.ACTION_MOVE -> {
                var xDelta = 0
                var yDelta = 0
                val layoutParams = v?.layoutParams as RelativeLayout.LayoutParams
                layoutParams.leftMargin = x - xDelta
                layoutParams.topMargin = y - yDelta
                layoutParams.rightMargin = 0
                layoutParams.bottomMargin = 0
                v.layoutParams = layoutParams
            }

            }
        val relative_layout = null
        relative_layout.invalidate()
        return true
    }

}

private fun Nothing?.invalidate() {

}


