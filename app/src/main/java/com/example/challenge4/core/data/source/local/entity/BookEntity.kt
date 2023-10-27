package com.example.challenge4.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val bookId: Int,

    @ColumnInfo(name = "book_name")
    val bookName: String,

    @ColumnInfo(name = "book_club")
    val bookClub: String,

    @ColumnInfo(name = "book_position")
    val bookPosition: String,

    @ColumnInfo(name = "book_nationaly")
    val bookNationality: String,

    @ColumnInfo(name = "book_description")
    val bookDescription: String,

    @ColumnInfo(name = "book_image")
    val bookImage: File,

    )