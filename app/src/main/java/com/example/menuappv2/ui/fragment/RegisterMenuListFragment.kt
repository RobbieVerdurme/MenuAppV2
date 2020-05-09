package com.example.menuappv2.ui.fragment

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menuappv2.R
import com.example.menuappv2.adapter.IngredientListAdapter
import com.example.menuappv2.databinding.RegisterMenuIngredientsBinding
import com.example.menuappv2.model.Ingredient
import com.example.menuappv2.viewmodel.RegisterMenuViewModel
import kotlinx.android.synthetic.main.register_menu_ingredients.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterMenuListFragment: Fragment() {
    val viewModel: RegisterMenuViewModel by sharedViewModel()
    private lateinit var recyclerview: RecyclerView
    private lateinit var ingredientAdapter: IngredientListAdapter
    private lateinit var addIngredientButton: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = RegisterMenuIngredientsBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        recyclerview = binding.listMenuIngredients
        addIngredientButton = binding.imgAddIngredient
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    fun setupFragment(){
        val swipeBackground: ColorDrawable = ColorDrawable(resources.getColor(R.color.colorAccent))
        val  deleteIcon: Drawable = ContextCompat.getDrawable(activity!!,R.drawable.ic_delete_black_24dp)!!

        ingredientAdapter = IngredientListAdapter(viewModel.getMenu().getIngredients() as ArrayList<Ingredient>)
        addIngredientButton.setOnClickListener {
            onAddIngredientClicked(
                Ingredient(
                    txtName.text.toString(),
                    txtQuantity.text.toString(),
                    spinnerMesurement.selectedItem.toString()
                )
            )
            // reset text
            txtName.text.clear()
            txtQuantity.text.clear()
        }
        recyclerview.apply {
            adapter = ingredientAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        // touch helper for swipe
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false //niet nodig voor dit programma
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                (ingredientAdapter as IngredientListAdapter).removeItem(p0)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemvView = viewHolder.itemView
                val iconMargin = (itemvView.height - deleteIcon.intrinsicHeight) / 2

                if(dX >0){
                    swipeBackground.setBounds(itemvView.left, itemvView.top, dX.toInt(), itemvView.bottom)
                    deleteIcon.setBounds(itemvView.left + iconMargin, itemvView.top + iconMargin, itemvView.left + iconMargin + deleteIcon.intrinsicWidth, itemvView.bottom - iconMargin)
                }else{
                    swipeBackground.setBounds(itemvView.left + dX.toInt(), itemvView.top, itemvView.right, itemvView.bottom)
                    deleteIcon.setBounds(itemvView.right - iconMargin - deleteIcon.intrinsicWidth, itemvView.top + iconMargin, itemvView.right - iconMargin, itemvView.bottom - iconMargin)
                }
                swipeBackground.draw(c)

                c.save()
                if(dX > 0 ) {
                    c.clipRect(itemvView.left, itemvView.top, dX.toInt(), itemvView.bottom)
                } else {
                    c.clipRect(itemvView.left + dX.toInt(), itemvView.top, itemvView.right, itemvView.bottom)
                }

                deleteIcon.draw(c)

                c.restore()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerview)
    }

    private fun onAddIngredientClicked(item: Ingredient) {
        val menu = viewModel.getMenu()
        val newIngredients: ArrayList<Ingredient> = menu.getIngredients() as ArrayList<Ingredient>
        newIngredients.add(item)
        menu.setIngredients(newIngredients)
        ingredientAdapter.setItems(menu.getIngredients() as ArrayList)
    }
}