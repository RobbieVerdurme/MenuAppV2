package com.example.menuappv2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.menuappv2.databinding.ItemIngredientListBinding
import com.example.menuappv2.model.Ingredient

class IngredientListAdapter(
    private val dataset: List<Ingredient>
): RecyclerView.Adapter<IngredientListAdapter.IngredientListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientListViewHolder {
        val binding = ItemIngredientListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: IngredientListViewHolder, position: Int) {
        val ingredient = dataset[position]
        holder.binding.ingredient = ingredient
        holder.binding.executePendingBindings()
    }

    /**************************************************inner class*****************************************************/
    class IngredientListViewHolder(val binding: ItemIngredientListBinding): RecyclerView.ViewHolder(binding.root)
}