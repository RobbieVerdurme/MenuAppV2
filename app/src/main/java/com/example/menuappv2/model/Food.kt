package com.example.menuappv2.model

class Food(
    private var key: String,
    private var name: String,
    private var ingredients: ArrayList<Ingredient>,
    private var description: String,
    var preperation: String,
    var createrMenu: String
) {
    constructor(): this("","",ArrayList<Ingredient>(),"","","")

    /**
     * @return key of the food
     */
    fun getKey(): String = key

    /**
     * @return name of food
     */
    fun getName(): String = name

    /**
     * @return description of food
     */
    fun getDescription(): String = description

    /**
     * @return the ingredients of the food
     */
    fun getIngredients(): List<Ingredient> = ingredients

    /**
     * set the key of the ingredient to newKey
     */
    fun setKey(newKey: String) {
        this.key = newKey
    }

    fun setName(newName: String){
        this.name = newName
    }

    fun setDescription(newDesciption: String){
        this.description = newDesciption
    }

    fun setIngredients(newIngredients: List<Ingredient>){
        this.ingredients = newIngredients as ArrayList
    }
}