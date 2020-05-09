package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.menuappv2.R
import com.example.menuappv2.databinding.FragmentMenuDecideBinding
import com.example.menuappv2.viewmodel.DecideViewModel
import com.example.menuappv2.viewmodel.MenuDetailViewModel
import kotlinx.android.synthetic.main.fragment_menu_decide.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuDecideFragment: Fragment() {
    /**
     * The [DecideViewModel] for this fragment.
     */
    val viewModel: DecideViewModel by viewModel()
    val detailViewModel: MenuDetailViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMenuDecideBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    fun setupFragment() {
        lblMenu.setOnClickListener {
            if(!lblMenu.text.contains("decide")){
                detailViewModel.setMenu(viewModel.getMenu())
                findNavController().navigate(R.id.menuDetailFragment)
            }
        }
    }
}