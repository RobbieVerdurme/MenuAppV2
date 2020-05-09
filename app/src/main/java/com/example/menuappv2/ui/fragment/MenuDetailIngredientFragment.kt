package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menuappv2.adapter.IngredientListAdapter
import com.example.menuappv2.databinding.MenuDetailIngredientsBinding
import com.example.menuappv2.model.Ingredient
import com.example.menuappv2.viewmodel.MenuDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuDetailIngredientFragment: Fragment() {
    /**
     * The [MenuDetailViewModel] for this fragment.
     */
    val viewModel: MenuDetailViewModel by sharedViewModel()

    /**
     * recyclerview config variables
     */
    private lateinit var recyclerview: RecyclerView
    private lateinit var ingredientAdapter: IngredientListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = MenuDetailIngredientsBinding.inflate(inflater, container, false)
        recyclerview = binding.listMenuIngredients
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    fun setupFragment(){
        ingredientAdapter = IngredientListAdapter(viewModel.getMenu().getIngredients() as ArrayList<Ingredient>)
        recyclerview.apply {
            adapter = ingredientAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}