package com.example.menuappv2.model

class Ingredient(
    private var name: String,
    var quantity: String,
    var measurement: String
) {
    constructor() : this("","","")

    /**
     * @return the name of the ingredient
     */
    fun getName(): String = name
}