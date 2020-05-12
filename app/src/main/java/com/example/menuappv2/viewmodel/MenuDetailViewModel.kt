package com.example.menuappv2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.menuappv2.model.Food
import com.example.menuappv2.network.MenuRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MenuDetailViewModel(
    private val repository: MenuRepository,
    application: Application): AndroidViewModel(application) {
    /************************************************variablen*********************************************************/
    private lateinit var menu:Food
    private var busy = MutableLiveData<Boolean>(false)

    /************************************************Methods***********************************************************/
    fun getMenu():Food = menu

    fun setMenu(newMenu: Food){
        menu = newMenu
    }

    fun deleteMenu(): Boolean {
        var completed= true
        if(!busy.value!!){
            viewModelScope.launch {
                busy.value = true
                val repoResult = async {
                    repository.remove(menu)
                }
                val completedRequest = repoResult.await()
                if(!completedRequest){
                    completed = false
                }
                busy.value = false
            }
            if(!completed){
                return false
            }
        }
        return false
    }
}