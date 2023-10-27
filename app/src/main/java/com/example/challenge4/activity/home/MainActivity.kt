package com.example.challenge4.activity.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge4.R
import com.example.challenge4.activity.addbook.AddActivity
import com.example.challenge4.activity.detail.DetailActivity
import com.example.challenge4.activity.edit.EditActivity
import com.example.challenge4.activity.login.LoginActivity
import com.example.challenge4.activity.user.ProfileActivity
import com.example.challenge4.adapter.BookAdapter
import com.example.challenge4.core.di.BookViewModelFactory
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeViewModel: HomeViewModel

    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        recyclerView = binding.rvBooks
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val factory = BookViewModelFactory.getInstance(this)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        homeViewModel.book.observe(this) { book ->
            if (book.isNotEmpty()) {
                visibilityEmptyData(false)
                bookAdapter = BookAdapter(book)
                recyclerView.adapter = bookAdapter

                bookAdapter.setOnItemClickCallback(object : BookAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Book) {
                        navigateToAnotherActivity(data, DetailActivity::class.java)
                    }

                    override fun onEditClicked(data: Book) {
                        navigateToAnotherActivity(data, EditActivity::class.java)
                    }

                    override fun onDeleteClicked(data: Book) {
                        dialogDelete(data)
                    }
                })
            } else {
                visibilityEmptyData(true)
            }
        }

        binding.fabAddBook.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        binding.btnExitApp.setOnClickListener {
            dialogLogout()
        }

        binding.btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun dialogDelete(book: Book) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hapus Book")
        builder.setMessage("Apakah anda yakin ingin menghapus book ini?")
        builder.setPositiveButton("Ya") { _, _ ->
            homeViewModel.deleteBook(book)
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

    private fun dialogLogout() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Apakah anda yakin ingin logout?")
        builder.setPositiveButton("Ya") { _, _ ->
            val sharedPreferences = getSharedPreferences("isUserLogin", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("isUserLogin", false)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

    private fun visibilityEmptyData(boolean: Boolean) {
        binding.emptyData.visibility = if (boolean) View.VISIBLE else View.GONE
        binding.emptyText.visibility = if (boolean) View.VISIBLE else View.GONE
        binding.rvBooks.visibility = if (boolean) View.GONE else View.VISIBLE
    }

    private fun navigateToAnotherActivity(book: Book, activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.putExtra("book", book)
        startActivity(intent)
    }

}