package com.luisfelipe.animefinder.presentation.details.episodes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.databinding.FragmentEpisodesBinding


class EpisodesFragment : Fragment(R.layout.fragment_episodes) {

    companion object {
        fun getInstance() = EpisodesFragment()
    }

    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodesBinding.bind(view)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}