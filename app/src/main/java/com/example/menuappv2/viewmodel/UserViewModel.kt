package com.example.menuappv2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.menuappv2.R
import com.example.menuappv2.model.User
import com.example.menuappv2.network.UserRepository

class UserViewModel(
    private val userRepository: UserRepository,
    application: Application
): AndroidViewModel(application) {
    private val user = MutableLiveData<User?>(null)
    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    private val usernameError = MutableLiveData<String>(null)
    private val passwordError = MutableLiveData<String>(null)
    private val usernameRequired: String = application.getString(R.string.login_username_required)
    private val passwordRequired: String = application.getString(R.string.login_password_required)

    private val usernameObserver = Observer<String>{onUsernameChanged(it)}
    private val passwordObserver = Observer<String>{onPasswordChanged(it)}

    private val busy = MutableLiveData<Boolean>(false)

    private val requestError = MutableLiveData<String>(null)

    fun getRequestError(): LiveData<String> = requestError
    fun getUsernameError(): LiveData<String> = usernameError
    fun getPasswordError():LiveData<String> = passwordError
    fun getLoggedInUser(): LiveData<User?> = user
    fun getBusy(): LiveData<Boolean> = busy
    /**
     * Getter that exposes [user] as [LiveData] to prevent writable leaks.
     */
    fun getUser(): LiveData<User?> = user


    init{
        username.observeForever(usernameObserver)
        password.observeForever(passwordObserver)
        //Reset the error values.
        //The above observers already trigger a validation with the default value.
        //Since thats an empty string, it will show errors.
        usernameError.value = null
        passwordError.value = null
    }

    private fun onUsernameChanged(charSequence: CharSequence){
        if(charSequence.isBlank()){
            usernameError.value = usernameRequired
        }
        else{
            usernameError.value = null
        }
    }

    private fun onPasswordChanged(charSequence: CharSequence){
        if(charSequence.isBlank()){
            passwordError.value = passwordRequired
        }
        else{
            passwordError.value = null
        }
    }

    /**
     * Validate all form inputs
     * @return whether the form is valid
     */
    fun validateForm(): Boolean {
        onUsernameChanged(username.value!!)
        onPasswordChanged(password.value!!)
        return usernameError.value == null && passwordError.value == null
    }

    /**
     * Remove [usernameObserver] and [passwordObserver] to prevents memory leaks.
     */
    override fun onCleared() {
        username.removeObserver(usernameObserver)
        password.removeObserver(passwordObserver)
        super.onCleared()
    }

    fun login(username:String, password: String){
        if(!busy.value!!){
            busy.value = true
            userRepository.login(username, password)
            busy.value = false
        }
    }

    fun setUser(newUser: User){
        user.value = newUser
    }
}