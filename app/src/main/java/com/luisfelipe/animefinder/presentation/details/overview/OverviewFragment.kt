package com.luisfelipe.animefinder.presentation.details.overview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.databinding.FragmentOverviewBinding
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.presentation.details.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment(R.layout.fragment_overview) {

    companion object {
        fun getInstance() = OverviewFragment()
    }

    private val viewModel: DetailsViewModel by activityViewModels()

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOverviewBinding.bind(view)

        initViewModelObservers()
    }

    private fun initViewModelObservers() {
        viewModel.animeDetails.observe(viewLifecycleOwner, { anime ->
            setOverview(anime.summary)
        })
    }

    private fun setOverview(overview: String?) {
        binding.overview.text = overview
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}