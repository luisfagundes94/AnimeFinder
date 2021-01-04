package com.luisfelipe.animefinder.presentation.details.episodes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.databinding.FragmentEpisodesBinding
import com.luisfelipe.animefinder.presentation.details.DetailsViewModel
import com.luisfelipe.animefinder.utils.verticalRecyclerViewLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class EpisodesFragment : Fragment(R.layout.fragment_episodes) {

    companion object {
        fun getInstance() = EpisodesFragment()
    }

    private val sharedViewModel: DetailsViewModel by activityViewModels()

    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!

    @Named("episodesAdapter")
    @Inject
    lateinit var episodesAdapter: EpisodesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodesBinding.bind(view)

        initRecyclerView()
        initSharedViewModelObservers()
    }

    private fun initRecyclerView() {
        binding.recyclerViewEpisodes.apply {
            setHasFixedSize(true)
            layoutManager = verticalRecyclerViewLayout()
            adapter = episodesAdapter
        }
    }

    private fun initSharedViewModelObservers() {
        sharedViewModel.apply {
            episodes.observe(viewLifecycleOwner, { episodes ->
                Log.d("episodes", episodes.toString())
                episodesAdapter.updateEpisodes(episodes)
            })
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}