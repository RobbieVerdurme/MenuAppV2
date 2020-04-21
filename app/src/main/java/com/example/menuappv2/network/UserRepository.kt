package com.example.menuappv2.network

import com.example.menuappv2.model.User

class UserRepository {
    fun loadApplicationUser() : DataOrError<User?> {
        return DataOrError(data = User())
    }

    fun login(username: String, password: String){

    }
}