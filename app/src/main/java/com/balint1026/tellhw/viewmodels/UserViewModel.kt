package com.balint1026.tellhw.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balint1026.tellhw.models.User
import com.balint1026.tellhw.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository()

    fun createUser(user: User) {
        viewModelScope.launch {
            userRepository.createUser(user)
        }
    }

    fun getUser(uid: String, callback: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUser(uid)
            callback(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(uid: String) {
        viewModelScope.launch {
            userRepository.deleteUser(uid)
        }
    }
}