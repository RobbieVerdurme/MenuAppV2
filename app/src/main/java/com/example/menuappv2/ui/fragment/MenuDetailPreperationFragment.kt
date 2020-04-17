package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.menuappv2.databinding.MenuDetailPreperationsBinding
import com.example.menuappv2.viewmodel.MenuDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuDetailPreperationFragment: Fragment() {
    /**
     * The [MenuDetailViewModel] for this fragment.
     */
    val viewModel: MenuDetailViewModel by sharedViewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = MenuDetailPreperationsBinding.inflate(inflater, container, false)
        binding.menu = viewModel.getMenu()
        return binding.root
    }
}