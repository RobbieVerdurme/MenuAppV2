package com.example.menuappv2.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.menuappv2.R
import com.example.menuappv2.model.Food
import com.example.menuappv2.network.MenuRepository
import kotlin.random.Random

class DecideViewModel(
    private val menuRepository: MenuRepository,
    private val application: Application
): ViewModel() {
    /************************************************variablen*********************************************************/
    private var foodList: LiveData<List<Food>> = menuRepository.getFoodList()
    private val chosenRandomfoodName = MutableLiveData<String>(application.getString(R.string.click_the_decide_button))

    /************************************************Methods***********************************************************/
    fun RandomFood() {
        if(foodList.value.isNullOrEmpty()){
            chosenRandomfoodName.value =  application.getString(R.string.No_Food_In_List)
        }else{
            val random = Random
            val randomId = random.nextInt(foodList.value!!.size)
            chosenRandomfoodName.value = foodList.value!![randomId].getName()
        }
    }

    fun getChosenRandomfoodName(): LiveData<String> {
        return chosenRandomfoodName
    }
}