package com.example.challenge4.activity.addbook

import androidx.lifecycle.ViewModel
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.core.domain.usecase.BookUseCase

class AddViewModel (private val bookUseCase: BookUseCase) : ViewModel() {

    fun addBook(book: Book) {
        bookUseCase.insertBook(book)
    }

}