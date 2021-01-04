package com.luisfelipe.animefinder.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.databinding.FragmentDetailsBinding
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.presentation.home.AnimeAdapter.Companion.ANIME_ID
import com.luisfelipe.animefinder.utils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by activityViewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animeId = arguments?.getInt(ANIME_ID)
        viewModel.getAnimeDetails(animeId)
        viewModel.getAnimeEpisodes(animeId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        initJobDetailsPagerAdapter()
        initTabLayout()
        initViewModelObservers()
    }

    private fun initTabLayout() {
            val tabLayoutMediator =
                TabLayoutMediator(
                    binding.tabLayout, binding.viewPager2
                ) { tab, position ->
                    when (position) {
                        0 -> tab.text = getString(R.string.tab_title_overview)
                        1 -> tab.text = getString(R.string.tab_title_extras)
                        2 -> tab.text = getString(R.string.tab_title_episodes)
                    }
                }
            tabLayoutMediator.attach()
    }

    private fun initJobDetailsPagerAdapter() {
        binding.viewPager2.adapter = DetailsViewPagerAdapter(requireActivity())
    }


    private fun initViewModelObservers() {
        viewModel.apply {
            animeDetails.observe(viewLifecycleOwner, { anime ->
                setUpAnimeInfo(anime)
            })
        }
    }

    private fun setUpAnimeInfo(anime: Anime) {
        binding.title.text = anime.title
        binding.score.text = anime.score.toString()
        binding.episodes.text = getString(R.string.dynamic_episodes, anime.episodes)
        binding.poster.load(anime.imageUrl)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}