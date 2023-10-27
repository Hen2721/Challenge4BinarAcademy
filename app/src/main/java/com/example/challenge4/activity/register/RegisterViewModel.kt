package com.example.challenge4.activity.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge4.core.domain.model.User
import com.example.challenge4.core.domain.usecase.UserUseCase

class RegisterViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun insertUser(user: User) = userUseCase.insertUser(user)

    fun getUserByEmailAndPassword(email: String, password: String): LiveData<User?> {
        return userUseCase.getUserByEmailAndPassword(email, password)
    }

    fun getUserByEmail(email: String): LiveData<User?> {
        return userUseCase.getUserByEmail(email)
    }
}