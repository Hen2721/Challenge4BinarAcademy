package com.example.challenge4.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.challenge4.core.data.source.local.entity.BookEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookEntity)

    @Delete
    fun deleteBook(book: BookEntity)

    @Update
    fun updateBook(book: BookEntity)

    @Query("SELECT * FROM book ORDER BY book_name ASC")
    fun getAllBook(): LiveData<List<BookEntity>>

    @Query("SELECT * FROM book WHERE id = :bookId")
    fun getBookById(bookId: Int): LiveData<BookEntity>

}