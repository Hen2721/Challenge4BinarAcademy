package com.example.challenge4.activity.addbook

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.R
import com.example.challenge4.activity.home.MainActivity
import com.example.challenge4.core.di.BookViewModelFactory
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.core.utils.reduceFileImage
import com.example.challenge4.core.utils.uriToFile
import com.example.challenge4.databinding.ActivityTambahdataBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahdataBinding
    private var currentImageUri: Uri? = null
    private lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tambahdata)

        val factory = BookViewModelFactory.getInstance(this)
        addViewModel = ViewModelProvider(this, factory)[AddViewModel::class.java]

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        binding.simpanBuku.setOnClickListener {
            if(validationInput()) {
                addBookToDatabase()
            }
        }

//        binding.bookImageEdit.setOnClickListener {
//            startGallery()
//        }

    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

//    private fun startGallery() {
//        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//    }

//    private val launcherGallery = registerForActivityResult(
//        ActivityResultContracts.PickVisualMedia()
//    ) { uri: Uri? ->
//        if (uri != null) {
//            currentImageUri = uri
//            binding.bookImageEdit.setText("Gambar berhasil dipilih")
//            binding.bookImagePreview.setImageURI(uri)
//        } else {
//            Log.d("Photo Picker", "No media selected")
//        }
//    }

    private fun addBookToDatabase() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val book = imageFile?.let {
            Book(
                bookId = 0,
                bookName = binding.masukkanNamaBuku.text.toString(),
                bookJenis = binding.rubahBuku.text.toString(),
                bookPenerbit = binding.penerbitEdit.text.toString(),
                bookPenulis = binding.penulisEdit.text.toString(),
                bookTahunTerbit =binding.tahunTerbitEdit.text.toString(),
                bookJumlahHalaman = binding.jumlahHalamanEdit.text.toString()
            )
        }

        addViewModel.addBook(book!!)
        Toast.makeText(this@AddActivity, "Data pemain berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@AddActivity, MainActivity::class.java)).apply {
            finishAffinity()
        }
    }

    private fun validationInput() : Boolean {
        var countError = 0

        if (binding.masukkanNamaBuku.text.isNullOrEmpty()) {
            binding.masukkanNamaBuku.error = "Nama tidak boleh kosong"
            countError++
        }
        if (binding.rubahBuku.text.isNullOrEmpty()) {
            binding.rubahBuku.error = "Jenis tidak boleh kosong"
            countError++
        }
        if (binding.penerbitEdit.text.isNullOrEmpty()) {
            binding.penerbitEdit.error = "Penerbit tidak boleh kosong"
            countError++
        }
        if (binding.penulisEdit.text.isNullOrEmpty()) {
            binding.penulisEdit.error = "Penulis tidak boleh kosong"
            countError++
        }
        if (binding.tahunTerbitEdit.text.isNullOrEmpty()) {
            binding.tahunTerbitEdit.error = "Tahun Terbit tidak boleh kosong"
            countError++
        }
        if (binding.jumlahHalamanEdit.text.isNullOrEmpty()) {
            binding.jumlahHalamanEdit.error = "Jumlah Halaman tidak boleh kosong"
            countError++
        }

        return countError == 0
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}