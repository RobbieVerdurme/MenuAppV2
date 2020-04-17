package com.example.menuappv2.adapter

import com.example.menuappv2.model.Food
/**
 * This interface defines a contract to detect clicks for the food item in an [Food] recyclerview item
 */
interface MenuClickListener {
    /**
     * A callback for handling [Food] list item selection
     * @param item the selected [Food]
     */
    fun onItemClicked(item: Food)
}