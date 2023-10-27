package com.example.challenge4.core.data.source.local.datasource

import androidx.lifecycle.LiveData
import com.example.challenge4.core.data.source.local.entity.BookEntity
import com.example.challenge4.core.data.source.local.room.BookDao

interface BookDataSource {

    fun insertBook(book: BookEntity)

    fun deleteBook(book: BookEntity)

    fun updateBook(book: BookEntity)

    fun getAllBook(): LiveData<List<BookEntity>>

    fun getBookById(bookId: Int): LiveData<BookEntity>
}

class BookDatabaseDataSource(private val bookDao: BookDao) :
    BookDataSource {

    companion object {
        private var instance: BookDatabaseDataSource? = null

        fun getInstance(bookDao: BookDao): BookDatabaseDataSource =
            instance ?: synchronized(this) {
                instance ?: BookDatabaseDataSource(bookDao)
            }
    }

    override fun insertBook(book: BookEntity) {
        bookDao.insertBook(book)
    }

    override fun deleteBook(book: BookEntity) {
        bookDao.deleteBook(book)
    }

    override fun updateBook(book: BookEntity) {
        bookDao.updateBook(book)
    }

    override fun getAllBook(): LiveData<List<BookEntity>> {
        return bookDao.getAllBook()
    }

    override fun getBookById(bookId: Int): LiveData<BookEntity> {
        return bookDao.getBookById(bookId)
    }
}