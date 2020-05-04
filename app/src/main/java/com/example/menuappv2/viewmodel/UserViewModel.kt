package com.example.menuappv2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.menuappv2.R
import com.example.menuappv2.network.DataError
import com.example.menuappv2.network.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    application: Application
): AndroidViewModel(application) {
    private val user = MutableLiveData<FirebaseUser?>(null)
    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val usernameError = MutableLiveData<String>(null)
    private val passwordError = MutableLiveData<String>(null)
    private val usernameRequired: String = application.getString(R.string.login_username_required)
    private val passwordRequired: String = application.getString(R.string.login_password_required)

    private val genericError = application.getString(R.string.generic_error)
    private val serverUnreachable = application.getString(R.string.login_io_exception)

    private val usernameObserver = Observer<String>{onUsernameChanged(it)}
    private val passwordObserver = Observer<String>{onPasswordChanged(it)}

    private val busy = MutableLiveData<Boolean>(false)

    private val requestError = MutableLiveData<String>(null)

    fun getRequestError(): LiveData<String> = requestError
    fun getUsernameError(): LiveData<String> = usernameError
    fun getPasswordError():LiveData<String> = passwordError
    fun getBusy(): LiveData<Boolean> = busy
    /**
     * Getter that exposes [user] as [LiveData] to prevent writable leaks.
     */
    fun getUser(): LiveData<FirebaseUser?> = user


    init{
        user.value = userRepository.loadApplicationUser()
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
            viewModelScope.launch {
                busy.value = true
                requestError.value = null
                val repoResult = async {
                    userRepository.login(username, password)
                }
                val dataOrError = repoResult.await()
                if(dataOrError.hasError()){
                    requestError.value = when(dataOrError.error){
                        DataError.API_INTERNAL_SERVER_ERROR -> serverUnreachable
                        else -> genericError
                    }
                }else {
                    user.value = dataOrError.data
                }
                busy.value = false
            }
        }
    }

    fun logout(){
        if(!busy.value!!){
            busy.value = true
            try{
                userRepository.logout()
                user.value = null
            }catch (e: Error){
                println(e)
            }
            busy.value = false
        }
    }
}