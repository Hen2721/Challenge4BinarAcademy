package com.example.challenge4.activity.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.challenge4.core.domain.model.User
import com.example.challenge4.core.domain.usecase.UserUseCase

class ProfileViewModel (private val userUseCase: UserUseCase) : ViewModel() {

    fun getUserByEmail(email: String): LiveData<User?> {
        return userUseCase.getUserByEmail(email)
    }

    fun updateUser(user: User) {
        userUseCase.updateUser(user)
    }

}