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
                bookJenis = it.bookClub,
                bookPenerbit = it.bookPosition,
                bookPenulis = it.bookNationality,
                bookTahunTerbit = it.bookDescription,
                bookImage = it.bookImage
            )
        }

    fun oneBookEntityToDomain(input: BookEntity): Book =
        Book(
            bookId = input.bookId,
            bookName = input.bookName,
            bookJenis = input.bookClub,
            bookPenerbit = input.bookPosition,
            bookPenulis = input.bookNationality,
            bookTahunTerbit = input.bookDescription,
            bookImage = input.bookImage
        )

    fun bookDomainToEntity(input: Book) = BookEntity(
        bookId = input.bookId,
        bookName = input.bookName,
        bookClub = input.bookJenis,
        bookPosition = input.bookPenerbit,
        bookNationality = input.bookPenulis,
        bookDescription = input.bookTahunTerbit,
        bookImage = input.bookImage
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