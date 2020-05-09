package com.example.menuappv2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.menuappv2.databinding.ItemIngredientListBinding
import com.example.menuappv2.model.Ingredient
import com.google.android.material.snackbar.Snackbar

class IngredientListAdapter(
    private var dataset: ArrayList<Ingredient>
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

    fun setItems(newItems: ArrayList<Ingredient>){
        this.dataset = newItems
        notifyDataSetChanged()
    }
    fun removeItem(viewHolder: RecyclerView.ViewHolder){
        val removedPosition = viewHolder.adapterPosition
        val removedItem = dataset[viewHolder.adapterPosition]
        dataset.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)

        Snackbar.make(viewHolder.itemView, "${removedItem.getName()} deleted.", Snackbar.LENGTH_LONG).setAction("UNDO") {
            dataset.add(removedPosition, removedItem)
            notifyItemInserted(removedPosition)
        }.show()
    }

    /**************************************************inner class*****************************************************/
    class IngredientListViewHolder(val binding: ItemIngredientListBinding): RecyclerView.ViewHolder(binding.root)
}