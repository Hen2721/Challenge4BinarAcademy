package com.example.challenge4.activity.edit

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.challenge4.R
import com.example.challenge4.activity.home.MainActivity
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.core.utils.reduceFileImage
import com.example.challenge4.core.utils.uriToFile
import com.example.challenge4.databinding.ActivityUbahBinding
import java.io.File

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUbahBinding
    private lateinit var editViewModel: EditViewModel
    private var currentImageUri: Uri? = null
    private var oldPhoto: File? = null
    private var bookId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ubah)

        val book = intent.getParcelableExtra<Book>("book") as Book
        bookId = book.bookId
//
//        Glide.with(this)
//            .load(book.bookImage)
//            .into(binding.bookImagePreview)
//
//        oldPhoto = book.bookImage
//        binding.bookImageEdit.setText("Gambar berhasil dipilih")
//
//        binding.bookNameEdit.setText(book.bookName)
//        binding.bookClubEdit.setText(book.bookClub)
//        binding.bookPositionEdit.setText(book.bookPosition)
//        binding.bookNationallyEdit.setText(book.bookNationality)
//        binding.bookDescEdit.setText(book.bookDescription)
//
//        val factory = BookViewModelFactory.getInstance(this)
//        editViewModel = ViewModelProvider(this, factory)[EditViewModel::class.java]
//
//        if (!allPermissionsGranted()) {
//            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
//        }
//
//        binding.saveBook.setOnClickListener {
//            if (validationInput()) {
//                updateBookToDatabase()
//            }
//        }
//
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

    private fun updateBookToDatabase() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val book = (if (currentImageUri != null) imageFile else oldPhoto)?.let {
            Book(
                bookId = bookId,
                bookName = binding.bookNameEdit.text.toString(),
                bookJenis = binding.bookJenisEdit.text.toString(),
                bookPenerbit = binding.bookPenerbitEdit.text.toString(),
                bookPenulis = binding.bookPenulisEdit.text.toString(),
                bookTahunTerbit =binding.bookTahunTerbitEdit.text.toString(),
                bookJumlahHalaman = binding.bookJumlahHalaman.text.toString()
            )
        }

        if (book != null) {
            editViewModel.updateBook(book)
        }
        Toast.makeText(this@EditActivity, "Data pemain berhasil diedit", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@EditActivity, MainActivity::class.java)).apply {
            finishAffinity()
        }
    }

    private fun validationInput(): Boolean {
        var countError = 0

        if (binding.bookNameEdit.text.isNullOrEmpty()) {
            binding.bookNameEdit.error = "Nama tidak boleh kosong"
            countError++
        }
        if (binding.bookJenisEdit.text.isNullOrEmpty()) {
            binding.bookJenisEdit.error = "Jenis tidak boleh kosong"
            countError++
        }
        if (binding.bookPenerbitEdit.text.isNullOrEmpty()) {
            binding.bookPenerbitEdit.error = "Penerbit tidak boleh kosong"
            countError++
        }
        if (binding.bookTahunTerbitEdit.text.isNullOrEmpty()) {
            binding.bookTahunTerbitEdit.error = "Penulis tidak boleh kosong"
            countError++
        }
        if (binding.bookJumlahHalaman.text.isNullOrEmpty()) {
            binding.bookJumlahHalaman.error = "Jumlah Halaman tidak boleh kosong"
            countError++
        }

        return countError == 0
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }

}