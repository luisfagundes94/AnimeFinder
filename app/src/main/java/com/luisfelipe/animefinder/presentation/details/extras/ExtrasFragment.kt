package com.luisfelipe.animefinder.presentation.details.extras

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.databinding.FragmentExtrasBinding
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.presentation.details.DetailsViewModel

class ExtrasFragment : Fragment(R.layout.fragment_extras) {

    companion object {
        fun getInstance() = ExtrasFragment()
    }

    private val viewModel: DetailsViewModel by activityViewModels()

    private var _binding: FragmentExtrasBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentExtrasBinding.bind(view)

        initViewModelObservers()
    }

    private fun initViewModelObservers() {
        viewModel.animeDetails.observe(viewLifecycleOwner, { anime ->
            setExtraAnimeDetails(anime)
        })
    }

    private fun setExtraAnimeDetails(anime: Anime) {
        binding.duration.text = anime.duration
        binding.status.text = anime.status
        binding.source.text = anime.source
        binding.premiered.text = anime.premiered
        binding.rating.text = anime.rating
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}