package com.example.challenge4.activity.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.challenge4.R
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.databinding.ActivityDetailbookBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailbookBinding

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailbook)

        val book = intent.getParcelableExtra<Book>("book") as Book

//        Glide.with(this)
//            .load(book.bookImage)
//            .into(binding.ivBook)

        binding.BookName.text = book.bookName
        binding.BookJenis.text = book.bookJenis
        binding.BookPenerbit.text = book.bookPenerbit
        binding.BookPenulis.text = book.bookPenulis
        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }

        binding.btnSearchGoogle.setOnClickListener {
            val bookName = book.bookName

            val searchQuery = "https://www.google.com/search?q=${bookName}"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchQuery))

            if (intent.resolveActivity(this.packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Tidak ada browser yang tersedia.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}