package com.example.menuappv2.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.menuappv2.ui.fragment.MenuDetailInfoFragment
import com.example.menuappv2.ui.fragment.MenuDetailIngredientFragment
import com.example.menuappv2.ui.fragment.MenuDetailPreperationFragment

class MenuDetailAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            when(position){
                0 -> return MenuDetailInfoFragment()
                1 -> return MenuDetailIngredientFragment()
                2 -> return MenuDetailPreperationFragment()
                else -> return Fragment()
            }
        }

}