package com.luisfelipe.animefinder.presentation.bookmarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding: FragmentBookmarksBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookmarksBinding.bind(view)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}