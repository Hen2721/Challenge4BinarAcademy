package com.example.challenge4.core.domain.usecase

import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.core.domain.repository.IBookRepository

class BookInteractor(private val bookRepository: IBookRepository) : BookUseCase {

    override fun insertBook(book: Book) = bookRepository.insertBook(book)

    override fun deleteBook(book: Book) = bookRepository.deleteBook(book)

    override fun updateBook(book: Book) = bookRepository.updateBook(book)

    override fun getAllBook() = bookRepository.getAllBook()

    override fun getBookById(bookId: Int) = bookRepository.getBookById(bookId)
}