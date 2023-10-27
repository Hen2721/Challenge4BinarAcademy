package com.example.challenge4.activity.home

import androidx.lifecycle.ViewModel
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.core.domain.usecase.BookUseCase

class HomeViewModel(private val bookUseCase: BookUseCase) : ViewModel() {

    val book = bookUseCase.getAllBook()

    fun deleteBook(book: Book) {
        bookUseCase.deleteBook(book)
    }
}