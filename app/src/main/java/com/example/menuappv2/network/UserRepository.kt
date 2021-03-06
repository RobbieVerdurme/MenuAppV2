package com.example.menuappv2.network

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val user = MutableLiveData<FirebaseUser>(null)
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        user.value = auth.currentUser
    }


    fun loadApplicationUser() : FirebaseUser? {
        return user.value
    }

    suspend fun login(username: String, password: String): DataOrError<FirebaseUser?>{
        var error: DataError = DataError.NO_ERROR
        auth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
            if(it.isSuccessful){
                user.value = auth.currentUser
            } else if (it.exception.toString().isEmpty()){
              error = DataError.API_NOT_FOUND
            } else {
                error = DataError.API_INTERNAL_SERVER_ERROR
            }
        }.await()
        return DataOrError(data = auth.currentUser, error = error)
    }

    fun logout() {
        auth.signOut()
        user.value = null
    }
}