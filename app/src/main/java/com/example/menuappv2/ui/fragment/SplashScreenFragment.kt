package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.menuappv2.R

/**
 * This [Fragment] represents a Splash Screen.
 * It is shown during app initialization.
 *
 * The splash screen shows the menuapp logo.
 * Note do not forget to hide the action bar when needed.
 */
class SplashScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onStart() {
        super.onStart()
        setupFragment()
    }


    private fun setupFragment(){
        //Since we are the Splash Screen we are looking for the NavController of the activity.
        val navController = findNavController()

        //We need to check the current destination, since we pass an action id
        if(navController.currentDestination?.id == R.id.splashScreenFragment){
            navController.navigate(R.id.action_splashScreenFragment_to_landingPageFragment)
        }
    }
}