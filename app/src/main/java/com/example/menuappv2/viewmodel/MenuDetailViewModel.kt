package com.example.menuappv2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.menuappv2.model.Food
import com.example.menuappv2.network.MenuRepository

class MenuDetailViewModel(
    private val repository: MenuRepository,
    application: Application): AndroidViewModel(application) {
    /************************************************variablen*********************************************************/
    private lateinit var menu:Food

    /************************************************Methods***********************************************************/
    fun getMenu():Food = menu

    fun setMenu(newMenu: Food){
        menu = newMenu
    }

    fun deleteMenu() {
        repository.remove(menu)
    }
}