package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.menuappv2.R
import com.example.menuappv2.databinding.FragmentProfileBinding
import com.example.menuappv2.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment: Fragment() {
    /**
     * The [UserViewModel] for this fragment.
     */
    val userViewModel: UserViewModel by sharedViewModel()

    /**
     * Set up the layout.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viemodel = userViewModel
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profiel, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout -> {
                userViewModel.logout()
                findNavController().navigate(R.id.loginFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    fun setupFragment(){
        if(userViewModel.getUser().value === null){
            findNavController().navigate(R.id.loginFragment)
        }
    }
}