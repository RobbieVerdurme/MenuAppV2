package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.menuappv2.R
import com.example.menuappv2.adapter.RegisterMenuAdapter
import com.example.menuappv2.databinding.FragmentRegisterMenuBinding
import com.example.menuappv2.viewmodel.RegisterMenuViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterMenuFragment: Fragment() {
    private lateinit var registerMenuAdapter: RegisterMenuAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    val viewModel: RegisterMenuViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRegisterMenuBinding.inflate(inflater, container, false)
        viewPager = binding.viewpager
        tabLayout = binding.tabLayout
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.register_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.save -> {
                if(viewModel.validateForm()){
                    if(!viewModel.saveMenu()){
                        Toast.makeText(context,"Error while adding menu to database",Toast.LENGTH_LONG).show()
                    }
                    findNavController().navigate(R.id.menuListFragment)
                }else {
                    Toast.makeText(context,"Error while adding menu",Toast.LENGTH_LONG).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    fun setupFragment() {
        registerMenuAdapter = RegisterMenuAdapter(this)
        viewPager.apply {
            adapter = registerMenuAdapter
        }
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            val text: String
            when(position){
                0 -> text = "Info"
                1 -> text = "Ingredients"
                2 -> text = "Preperation"
                else -> text = "Error"
            }
            tab.text = text
        }.attach()
    }
}