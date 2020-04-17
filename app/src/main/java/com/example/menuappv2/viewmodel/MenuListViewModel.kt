package com.example.menuappv2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.menuappv2.model.Food
import com.example.menuappv2.network.MenuRepository

class MenuListViewModel(
    private val menuRepository: MenuRepository,
    application: Application
): AndroidViewModel(application) {
    /************************************************variablen*********************************************************/
    val filterText: MutableLiveData<String> = MutableLiveData<String>("")
    private lateinit var selectedMenu: Food

    /***********************************************get & set**********************************************************/
    /**
     * @return foodlist
     */
    fun getFoodList() : List<Food> {
        val foodList = menuRepository.getFoodList()
        if(foodList.value === null){
            return ArrayList<Food>()
        }else{
            return foodList.value!!
        }
    }

    /**
     * set selected menu
     */
    fun setSelectedMenu(menu: Food){
        selectedMenu = menu
    }
}