package com.example.menuappv2.network

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.menuappv2.model.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class MenuRepository {
    private var liveFoodList :MutableLiveData<ArrayList<Food>> = MutableLiveData<ArrayList<Food>>(arrayListOf())
    private var foodList = ArrayList<Food>()
    private val firebaseDatabase = Firebase.database
    private val databaseRefrenceData = firebaseDatabase.getReference("FoodList")

    init {
        databaseRefrenceData.keepSynced(true)
        
        databaseRefrenceData.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("Failed to read value ${p0.message}")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                foodList.clear()
                for (h in dataSnapshot.child("Food").children){
                    val food = h.getValue(Food::class.java)
                    food?.setKey(h.key!!)
                    foodList.add(food!!)
                }
                liveFoodList.value!!.clear()
                liveFoodList.value!!.addAll(foodList)
            }
        })
    }

    fun getFoodList(): LiveData<List<Food>> {
        return liveFoodList as LiveData<List<Food>>
    }

    fun remove(food:Food): Boolean{
        databaseRefrenceData.child("Food").child(food.getKey()).removeValue()
        liveFoodList.value!!.remove(food)
        return true
    }

    fun save(food : Food): Boolean{
        if(food.getKey().isEmpty()){
            val key = databaseRefrenceData.child("Food").push().key
            if(key != null){
                food.setKey(key)
                databaseRefrenceData.child("Food").push().setValue(food)
                liveFoodList.value!!.add(food)
                return true
            }
        }else{
            databaseRefrenceData.child("Food").child(food.getKey()).setValue(food)
            val index = liveFoodList.value!!.indexOf(food)
            liveFoodList.value!![index] = food
            return true
        }
        return false
    }
}