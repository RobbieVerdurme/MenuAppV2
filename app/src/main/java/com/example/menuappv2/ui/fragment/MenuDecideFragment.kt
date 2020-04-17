package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.menuappv2.databinding.FragmentMenuDecideBinding
import com.example.menuappv2.viewmodel.DecideViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuDecideFragment: Fragment() {
    /**
     * The [DecideViewModel] for this fragment.
     */
    val viewModel: DecideViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMenuDecideBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}