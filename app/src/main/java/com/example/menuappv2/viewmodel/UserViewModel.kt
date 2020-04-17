package com.example.menuappv2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menuappv2.model.User
import com.example.menuappv2.model.UserLoginState
import com.example.menuappv2.network.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel () {
    /**
     * A [LiveData] that stores the user's login state.
     * This will be consumed by objects that only need to know whether the user is logged in or not.
     *
     * We initialize with a default value of [UserLoginState.UNKNOWN].
     */
    private val userState = MutableLiveData<UserLoginState>(UserLoginState.UNKNOWN)
    private val user = MutableLiveData<User?>(null)

    /**
     * Getter that exposes [user] as [LiveData] to prevent writable leaks.
     */
    fun getUser(): LiveData<User?> = user

    /**
     * Getter that exposes [userState] as [LiveData] to prevent writable leaks.
     */
    fun getUserState(): LiveData<UserLoginState> = userState

    fun loadUser(){
        viewModelScope.launch {
            val loadUser = async {
                userRepository.loadApplicationUser()
            }
            val data = loadUser.await()
            if(data.data == null){
                userState.value = UserLoginState.LOGGED_OUT
            }else{
                user.value = data.data
                userState.value = UserLoginState.LOGGED_IN
            }
        }
    }
}