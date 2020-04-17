package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menuappv2.R
import com.example.menuappv2.adapter.MenuClickListener
import com.example.menuappv2.adapter.MenuFoodListAdapter
import com.example.menuappv2.databinding.FragmentMenuListBinding
import com.example.menuappv2.model.Food
import com.example.menuappv2.viewmodel.MenuDetailViewModel
import com.example.menuappv2.viewmodel.MenuListViewModel
import kotlinx.android.synthetic.main.fragment_menu_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuListFragment : Fragment(), MenuClickListener {
    /**
     * The [MenuListViewModel] for this fragment.
     */
    val viewModel: MenuListViewModel by viewModel()
    val menuDetailViewModel: MenuDetailViewModel by sharedViewModel()

    /**
     * the adapter for this fragment
     */
    private lateinit var menuListAdapter: MenuFoodListAdapter

    private lateinit var recyclerview: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMenuListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        recyclerview = binding.listFood
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    override fun onResume() {
        super.onResume()
        (listFood.adapter as Filterable).filter.filter(txtFilter.text.toString())
        listFood.adapter?.notifyDataSetChanged()
    }

    /**
     * setup fragment
     */
    fun setupFragment() {
        menuListAdapter = MenuFoodListAdapter(this,viewModel.getFoodList())
        recyclerview.apply {
            adapter = menuListAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        txtFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                (listFood.adapter as Filterable).filter.filter(txtFilter.text.toString())
            }
        })
    }

    /**
     * redirect to menudetail fragment
     */
    override fun onItemClicked(item: Food) {
        menuDetailViewModel.setMenu(item)
        findNavController().navigate(R.id.menuDetailFragment)
    }
}