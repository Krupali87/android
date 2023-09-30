package com.app.videoplayer.Activity

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.app.videoplayer.Fragment.FoldersFragment
import com.app.videoplayer.Fragment.MusicFragment
import com.app.videoplayer.Fragment.VideosFragment
import com.app.videoplayer.R
import com.app.videoplayer.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setFragment(VideosFragment())
        requestRuntimePermission()



        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId)
            {
                R.id.videoview -> setFragment(VideosFragment())
                R.id.folderview -> setFragment(FoldersFragment())
                R.id.musicview -> setFragment(MusicFragment())

            }
            return@setOnItemSelectedListener true
        }
    }

    private fun setFragment(fragment: Fragment)
    {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentfl,fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    private fun requestRuntimePermission() : Boolean{

        if (ActivityCompat.checkSelfPermission(this,WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE),101)
            return false
        }
        return true

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            else
                ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE),101)

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}