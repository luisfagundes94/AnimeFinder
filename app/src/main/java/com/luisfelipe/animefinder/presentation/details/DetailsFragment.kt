package com.luisfelipe.animefinder.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.databinding.FragmentDetailsBinding
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.presentation.home.AnimeAdapter.Companion.ANIME_ID
import com.luisfelipe.animefinder.utils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animeId = arguments?.getInt(ANIME_ID)
        viewModel.getAnimeDetails(animeId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        initViewModelObservers()
    }

    private fun initViewModelObservers() {
        viewModel.apply {
            animeDetails.observe(viewLifecycleOwner, { anime ->
                setUpAnimeInfo(anime)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpAnimeInfo(anime: Anime) {
        binding.title.text = anime.title
        binding.score.text = anime.score.toString()
        binding.episodes.text = "Episodes: ${anime.episodes}"
        binding.poster.load(anime.imageUrl)
        binding.summary.text = anime.summary
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}