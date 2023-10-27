package com.example.challenge4.core.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.activity.addbook.AddViewModel
import com.example.challenge4.activity.edit.EditViewModel
import com.example.challenge4.activity.home.HomeViewModel
import com.example.challenge4.core.domain.usecase.BookUseCase

class BookViewModelFactory private constructor(private val bookUseCase: BookUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: BookViewModelFactory? = null

        fun getInstance(context: Context): BookViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: BookViewModelFactory(
                    Injection.provideBookUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(bookUseCase) as T
            }
            modelClass.isAssignableFrom(AddViewModel::class.java) -> {
                AddViewModel(bookUseCase) as T
            }
            modelClass.isAssignableFrom(EditViewModel::class.java) -> {
                EditViewModel(bookUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}