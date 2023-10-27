package com.example.challenge4.activity.edit

import androidx.lifecycle.ViewModel
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.core.domain.usecase.BookUseCase

class EditViewModel (private val bookUseCase: BookUseCase) : ViewModel() {

    fun updateBook(book: Book) {
        bookUseCase.updateBook(book)
    }

}