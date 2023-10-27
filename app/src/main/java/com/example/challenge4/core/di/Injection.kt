package com.example.challenge4.core.di

import android.content.Context
import com.example.challenge4.core.data.BookRepository
import com.example.challenge4.core.data.UserRepository
import com.example.challenge4.core.data.source.local.datasource.BookDatabaseDataSource
import com.example.challenge4.core.data.source.local.datasource.UserDatabaseDataSource
import com.example.challenge4.core.data.source.local.room.AppDatabase
import com.example.challenge4.core.domain.repository.IBookRepository
import com.example.challenge4.core.domain.repository.IUserRepository
import com.example.challenge4.core.domain.usecase.BookInteractor
import com.example.challenge4.core.domain.usecase.BookUseCase
import com.example.challenge4.core.domain.usecase.UserInteractor
import com.example.challenge4.core.domain.usecase.UserUseCase
import com.example.challenge4.core.utils.AppExecutors

object Injection {
    private fun provideBookRepository(context: Context): IBookRepository {
        val database = AppDatabase.getInstance(context)

        val localDataSource = BookDatabaseDataSource.getInstance(database.bookDao())
        val appExecutors = AppExecutors()

        return BookRepository.getInstance(localDataSource, appExecutors)
    }

    private fun provideUserRepository(context: Context): IUserRepository {
        val database = AppDatabase.getInstance(context)

        val localDataSource = UserDatabaseDataSource.getInstance(database.userDao())
        val appExecutors = AppExecutors()

        return UserRepository.getInstance(localDataSource, appExecutors)
    }

    fun provideBookUseCase(context: Context): BookUseCase {
        val repository = provideBookRepository(context)
        return BookInteractor(repository)
    }

    fun provideUserUseCase(context: Context): UserUseCase {
        val repository = provideUserRepository(context)
        return UserInteractor(repository)
    }
}