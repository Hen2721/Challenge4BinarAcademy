package com.example.challenge4.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.challenge4.core.data.source.local.datasource.BookDatabaseDataSource
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.core.domain.repository.IBookRepository
import com.example.challenge4.core.utils.AppExecutors
import com.example.challenge4.core.utils.DataMapper

class BookRepository private constructor(
    private val bookDatabaseDataSource: BookDatabaseDataSource,
    private val appExecutors: AppExecutors
) : IBookRepository {

    companion object {
        @Volatile
        private var instance: BookRepository? = null

        fun getInstance(
            bookData: BookDatabaseDataSource,
            appExecutors: AppExecutors
        ): BookRepository =
            instance ?: synchronized(this) {
                instance ?: BookRepository(bookData, appExecutors)
            }
    }

    override fun insertBook(book: Book) {
        val bookEntity = DataMapper.bookDomainToEntity(book)
        appExecutors.diskIO().execute { bookDatabaseDataSource.insertBook(bookEntity) }
    }

    override fun deleteBook(book: Book) {
        val bookEntity = DataMapper.bookDomainToEntity(book)
        appExecutors.diskIO().execute { bookDatabaseDataSource.deleteBook(bookEntity) }
    }

    override fun updateBook(book: Book) {
        val bookEntity = DataMapper.bookDomainToEntity(book)
        appExecutors.diskIO().execute { bookDatabaseDataSource.updateBook(bookEntity) }
    }

    override fun getAllBook(): LiveData<List<Book>> {
        return bookDatabaseDataSource.getAllBook().map { DataMapper.userMapEntitiesToDomain(it) }
    }

    override fun getBookById(bookId: Int): LiveData<Book> {
        return bookDatabaseDataSource.getBookById(bookId)
            .map { DataMapper.oneBookEntityToDomain(it) }
    }


}