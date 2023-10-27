package com.example.challenge4.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge4.core.domain.model.User
import com.example.challenge4.core.domain.usecase.BookUseCase
import com.example.challenge4.core.domain.usecase.UserUseCase

class LoginViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getUserByEmailAndPassword(email: String, password: String): LiveData<User?> {
        return userUseCase.getUserByEmailAndPassword(email, password)
    }
}