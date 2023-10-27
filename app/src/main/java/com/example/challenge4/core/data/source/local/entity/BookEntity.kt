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

    @ColumnInfo(name = "book_jenis")
    val bookJenis: String,

    @ColumnInfo(name = "book_penerbit")
    val bookPenerbit: String,

    @ColumnInfo(name = "book_penulis")
    val bookPenulis: String,

    @ColumnInfo(name = "book_tahunterbit")
    val bookTahunTerbit: String,

    @ColumnInfo(name = "book_jumlahhalaman")
    val bookJumlahHalaman: String,

    )