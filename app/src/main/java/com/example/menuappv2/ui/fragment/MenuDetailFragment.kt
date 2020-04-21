package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.menuappv2.R
import com.example.menuappv2.adapter.MenuDetailAdapter
import com.example.menuappv2.databinding.FragmentMenuDetailBinding
import com.example.menuappv2.viewmodel.MenuDetailViewModel
import com.example.menuappv2.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_menu_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuDetailFragment: Fragment() {
    /**
     * The [MenuDetailViewModel] for this fragment.
     */
    val viewModel: MenuDetailViewModel by sharedViewModel()

    val uservm: UserViewModel by sharedViewModel()

    /**
     * the tabconfiguration for the fragment
     */
    private lateinit var menuDetailAdapter: MenuDetailAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMenuDetailBinding.inflate(inflater, container, false)
        tabLayout = binding.tabLayout
        viewPager = binding.viewpager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if(uservm.getUser().value !== null) {
            inflater.inflate(R.menu.menu_detail, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.editMenu -> {
                findNavController().navigate(R.id.menuDetailFragment)
                true
            }
            R.id.deleteMenu -> {
                findNavController().navigate(R.id.menuDetailFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setupFragment(){
        menuDetailAdapter = MenuDetailAdapter(this)
        viewPager.apply {
            adapter = menuDetailAdapter
        }
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            var text: String
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