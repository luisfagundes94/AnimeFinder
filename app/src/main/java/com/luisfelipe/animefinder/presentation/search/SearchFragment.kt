package com.luisfelipe.animefinder.presentation.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}