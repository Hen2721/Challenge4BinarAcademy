package com.example.challenge4.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.challenge4.core.data.Resource
import com.example.challenge4.core.domain.model.Book

interface IBookRepository {

    fun insertBook(book: Book)

    fun deleteBook(book: Book)

    fun updateBook(book: Book)

    fun getAllBook(): LiveData<List<Book>>

    fun getBookById(bookId: Int): LiveData<Book>

}