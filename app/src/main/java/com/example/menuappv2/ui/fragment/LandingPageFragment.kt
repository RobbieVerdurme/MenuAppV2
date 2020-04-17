package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.menuappv2.R
import com.example.menuappv2.viewmodel.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LandingPageFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_landing_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    /**
     * Setup this fragment
     */
    private fun setupFragment() {
        //Show the toolbar since we are the landing page
        (activity as AppCompatActivity).supportActionBar!!.show()
        val bottomNavigation = view?.findViewById<BottomNavigationView>(R.id.landingPageBottomNavigation)
        //Find the nested nav host
        val navController = Navigation.findNavController(view?.findViewById<View>(R.id.landingPageNavHost)!!)
        NavigationUI.setupWithNavController(bottomNavigation!!,navController)
    }
}