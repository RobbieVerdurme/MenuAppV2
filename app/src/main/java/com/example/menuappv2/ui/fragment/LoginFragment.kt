package com.example.menuappv2.ui.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.menuappv2.R
import com.example.menuappv2.databinding.FragmentLoginBinding
import com.example.menuappv2.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment: Fragment() {
    /**
     * The [UserViewModel] for this fragment.
     */
    val userViewModel: UserViewModel by sharedViewModel()

    /**
     * Set up the layout.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = userViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    /**
     * Setup this [Fragment]
     */
    private fun setupFragment() {
        //Set the title and show the toolbar
        val toolbar = (activity as AppCompatActivity).supportActionBar!!
        view?.findViewById<Button>(R.id.login)?.setOnClickListener{onLoginClick()}
        toolbar.title = getString(R.string.login_title)
        toolbar.show()
    }

    override fun onStart() {
        super.onStart()
        setupObservers()
    }

    /**
     *  Setup the [Observer]s
     */
    private fun setupObservers(){
        val navController = findNavController()
        userViewModel.getUser().observe(viewLifecycleOwner, Observer{
            if(it != null){
                if(navController.currentDestination?.id == R.id.loginFragment){
                    navController.navigate(R.id.action_loginFragment_to_menuListFragment)
                }
            }
        })
        userViewModel.getRequestError().observe(viewLifecycleOwner, Observer {
            if(it != null){
                    Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
            }
        })
    }


    /**
     * Process to login
     */
    private fun onLoginClick(){
        if(userViewModel.validateForm()){
            userViewModel.login(userViewModel.username.value.toString(), userViewModel.password.value.toString())
        }
    }
}