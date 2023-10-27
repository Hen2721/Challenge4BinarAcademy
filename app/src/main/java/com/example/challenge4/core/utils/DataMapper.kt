package com.example.challenge4.core.utils

import com.example.challenge4.core.data.source.local.entity.BookEntity
import com.example.challenge4.core.data.source.local.entity.UserEntity
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.core.domain.model.User

object DataMapper {

    fun userMapEntitiesToDomain(input: List<BookEntity>): List<Book> =
        input.map {
            Book(
                bookId = it.bookId,
                bookName = it.bookName,
                bookJenis = it.bookJenis,
                bookPenerbit = it.bookPenerbit,
                bookPenulis = it.bookPenulis,
                bookTahunTerbit = it.bookTahunTerbit,
                bookJumlahHalaman = it.bookJumlahHalaman,
            )
        }

    fun oneBookEntityToDomain(input: BookEntity): Book =
        Book(
            bookId = input.bookId,
            bookName = input.bookName,
            bookJenis = input.bookJenis,
            bookPenerbit = input.bookPenerbit,
            bookPenulis = input.bookPenulis,
            bookTahunTerbit = input.bookTahunTerbit,
            bookJumlahHalaman = input.bookJumlahHalaman
        )

    fun bookDomainToEntity(input: Book) = BookEntity(
        bookId = input.bookId,
        bookName = input.bookName,
        bookJenis = input.bookJenis,
        bookPenerbit = input.bookPenerbit,
        bookPenulis = input.bookPenulis,
        bookTahunTerbit = input.bookTahunTerbit,
        bookJumlahHalaman = input.bookJumlahHalaman
    )

    fun userDomainToUserEntity(input: User) = UserEntity(
        id = input.id ?: 0,
        name = input.name,
        username = input.username,
        email = input.email,
        phone = input.phone,
        password = input.password
    )

    fun userLoginEntityToUserDomain(input: UserEntity?) =
        if (input == null) {
            null
        } else {
            User(
                id = input.id,
                name = input.name,
                username = input.username,
                email = input.email,
                phone = input.phone,
                password = input.password
            )
        }

    fun userEntityToUserDomain(input: UserEntity) =
        User(
            id = input.id,
            name = input.name,
            username = input.username,
            email = input.email,
            phone = input.phone,
            password = input.password
        )
}