package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.menuappv2.databinding.MenuDetailInfoBinding
import com.example.menuappv2.viewmodel.MenuDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuDetailInfoFragment: Fragment() {
    /**
     * The [MenuDetailViewModel] for this fragment.
     */
    val viewModel: MenuDetailViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = MenuDetailInfoBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }
}