package com.example.menuappv2.network

import androidx.lifecycle.MutableLiveData
import com.example.menuappv2.model.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class MenuRepository {
    private var liveFoodList :MutableLiveData<List<Food>> = MutableLiveData<List<Food>>()
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
                liveFoodList.value = foodList as List<Food>
            }
        })
    }

    fun getFoodList(): MutableLiveData<List<Food>>{
        if(liveFoodList.value == null){
            liveFoodList.value = foodList as List<Food>
        }
        return liveFoodList
    }

    fun remove(food:Food){
        databaseRefrenceData.child("Food").child(food.getKey()).removeValue()
    }

    fun save(food : Food){
        if(food.getKey().isEmpty()){
            databaseRefrenceData.child("Food").push().setValue(food)
        }else{
            databaseRefrenceData.child("Food").child(food.getKey()).setValue(food)
        }
    }
}