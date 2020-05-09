package com.example.menuappv2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.menuappv2.model.Food
import com.example.menuappv2.network.MenuRepository

class RegisterMenuViewModel(private val repository: MenuRepository, application: Application): AndroidViewModel(application) {
    /************************************************variablen*********************************************************/
    private lateinit var menu: Food
    // ERROR
    private val foodInfoError = MutableLiveData<String>(null)
    private val foodPreperationError = MutableLiveData<String>(null)

    fun getFoodInfoError(): LiveData<String> = foodInfoError
    fun getPreperationError() :LiveData<String> = foodPreperationError


    // PROPS
    val foodName = MutableLiveData<String>("")
    val foodDescription = MutableLiveData<String>("")
    val foodPreperation = MutableLiveData<String>("")

    // OBSERVER
    private val foodNameObserver = Observer<String>{onFoodNameChanged(it)}
    private val foodDescriptionObserver = Observer<String>{onFoodDescriptionChanged(it)}
    private val foodPreperationObserver = Observer<String>{onFoodPreperationChanged(it)}

    /************************************************Methods***********************************************************/
    init {
        foodName.observeForever(foodNameObserver)
        foodDescription.observeForever(foodDescriptionObserver)
        foodPreperation.observeForever(foodPreperationObserver)
        //Reset the error values.
        //The above observers already trigger a validation with the default value.
        //Since thats an empty string, it will show errors.
        foodInfoError.value = null
        foodPreperationError.value = null
    }
    fun getMenu():Food = menu

    fun setMenu(newMenu: Food) {
        menu = newMenu
        foodName.value = menu.getName()
        foodDescription.value = menu.getDescription()
        foodPreperation.value = menu.preperation
    }

    /**
     * Validate all form inputs
     * @return whether the form is valid
     */
    fun validateForm(): Boolean {
        onFoodNameChanged(foodName.value!!)
        onFoodDescriptionChanged(foodDescription.value!!)
        onFoodPreperationChanged(foodPreperation.value!!)
        return if(foodInfoError.value == null && foodPreperationError.value == null){
            foodName.value = ""
            foodDescription.value = ""
            foodPreperation.value = ""
            true
        }else {
            false
        }
    }

    private fun onFoodNameChanged(charSequence: CharSequence) {
        if(charSequence.isBlank()){
            foodInfoError.value = "required"
        }else {
            foodInfoError.value = null
            menu.setName(charSequence.toString())
        }
    }
    private fun onFoodDescriptionChanged(charSequence: CharSequence) {
        if(charSequence.isBlank()){
            foodInfoError.value = "required"
        }else {
            foodInfoError.value = null
            menu.setDescription(charSequence.toString())
        }
    }
    private fun onFoodPreperationChanged(charSequence: CharSequence){
        if(charSequence.isBlank()){
            foodPreperationError.value = "required"
        }else {
            foodPreperationError.value = null
            menu.preperation = charSequence.toString()
        }
    }

    override fun onCleared() {
        super.onCleared()
        foodName.removeObserver(foodNameObserver)
        foodDescription.removeObserver(foodDescriptionObserver)
        foodPreperation.removeObserver(foodPreperationObserver)
    }

    fun saveMenu(){
        repository.save(menu)
    }
}