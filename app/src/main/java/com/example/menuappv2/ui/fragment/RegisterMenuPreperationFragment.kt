package com.example.menuappv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.menuappv2.databinding.RegisterMenuPreperationsBinding
import com.example.menuappv2.viewmodel.RegisterMenuViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterMenuPreperationFragment: Fragment() {
    val viewModel: RegisterMenuViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = RegisterMenuPreperationsBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }
}