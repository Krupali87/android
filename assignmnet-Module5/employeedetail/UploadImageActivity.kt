package com.example.myapplication

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.myapplication.databinding.ActivityUploadImageBinding
import net.gotev.uploadservice.protocols.multipart.MultipartUploadRequest

class UploadImageActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUploadImageBinding
    private lateinit var filePath: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnselect.setOnClickListener(this)
        binding.buttonupload.setOnClickListener(this)

        requestPermission()
    }

    fun requestPermission() {
        if (checkSelfPermission(READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE), 100)
        } else {
            Toast.makeText(
                applicationContext,
                "External storage permission already granted.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnselect -> {
                val i = Intent()
                i.type = "image/*"
                i.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(i, "Select image"), 1)
            }

            binding.buttonupload -> {
                val name = binding.edtname.text.toString()
                val path = getPath(filePath)
                MultipartUploadRequest(this, Api.url)
                    .addFileToUpload(path, "url")
                    .addParameter("name", name)
                    .setMaxRetries(2)
                    .startUpload()
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, ProfileActivity::class.java))
                finish()
            }
        }
    }

    @SuppressLint("Range")
    private fun getPath(filePath: Uri): String {
        var cursor = contentResolver.query(filePath!!, null, null, null, null)
        cursor!!.moveToFirst()
        var documentId = cursor.getString(0)
        documentId = documentId.substring(documentId.lastIndexOf(":") + 1)
        cursor.close()
        cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            MediaStore.Images.Media._ID + " = ? ",
            arrayOf(documentId),
            null,
        )
        cursor!!.moveToFirst()
        val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor.close()
        return path
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            filePath = data.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
            binding.img.setImageBitmap(bitmap)
        }
    }
}