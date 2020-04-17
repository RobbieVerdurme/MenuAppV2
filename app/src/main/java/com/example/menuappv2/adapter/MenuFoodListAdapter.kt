package com.example.menuappv2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.menuappv2.databinding.ItemFoodLlistBinding
import com.example.menuappv2.model.Food

class MenuFoodListAdapter(
    private val clickListener: MenuClickListener,
    private val dataset: List<Food>
): RecyclerView.Adapter<MenuFoodListAdapter.MenuFoodListViewHolder>(), Filterable{
    /************************************************variablen*********************************************************/
    private var filterListResult: ArrayList<Food>
    private lateinit var charSearch: String

    /**************************************************init************************************************************/
    init {
        filterListResult = ArrayList(dataset)
    }

    /************************************************Override**********************************************************/
    override fun getItemCount(): Int = filterListResult.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuFoodListViewHolder {
        val binding = ItemFoodLlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuFoodListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuFoodListViewHolder, position: Int) {
        val menu = dataset[position]
        holder.binding.menu = menu
        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(menu)
        }
        holder.binding.executePendingBindings()
    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                charSearch = constraint.toString()
                filter()
                val filterResults = Filter.FilterResults()
                filterResults.values = filterListResult
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterListResult = results?.values as ArrayList<Food>
                notifyDataSetChanged()
            }
        }
    }

    /************************************************Methods***********************************************************/
    fun filter() {
        if(charSearch.isEmpty()){
            filterListResult = ArrayList(dataset)
        }else{
            val resultList = ArrayList<Food>()
            for (row in dataset){
                if(row.getName().toLowerCase().contains(charSearch.toLowerCase()) or row.getIngredients().any { x -> x.getName().toLowerCase().contains(charSearch.toLowerCase())})
                    resultList.add(row)
            }
            filterListResult = resultList
        }
    }
    /**************************************************inner class*****************************************************/
    class MenuFoodListViewHolder(val binding: ItemFoodLlistBinding): RecyclerView.ViewHolder(binding.root)
}