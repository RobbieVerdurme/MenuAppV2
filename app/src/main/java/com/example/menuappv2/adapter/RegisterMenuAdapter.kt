package com.example.menuappv2.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.menuappv2.ui.fragment.RegisterMenuInfoFragment
import com.example.menuappv2.ui.fragment.RegisterMenuListFragment
import com.example.menuappv2.ui.fragment.RegisterMenuPreperationFragment

class RegisterMenuAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            when(position){
                0 -> return RegisterMenuInfoFragment()
                1 -> return RegisterMenuListFragment()
                2 -> return RegisterMenuPreperationFragment()
                else -> return Fragment()
            }
        }
}