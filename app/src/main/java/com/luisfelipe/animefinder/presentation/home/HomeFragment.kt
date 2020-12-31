package com.luisfelipe.animefinder.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.databinding.FragmentHomeBinding
import com.luisfelipe.animefinder.utils.horizontalRecyclerViewLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    @Named("animeAdapter")
    lateinit var popularAnimesAdapter: AnimeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)


        initPopularAnimesRecyclerView()
        initViewModelObservers()

        viewModel.getPopularAnimes()
    }

    private fun initPopularAnimesRecyclerView() {
        binding.recyclerViewPopular.apply {
            setHasFixedSize(true)
            layoutManager = horizontalRecyclerViewLayout()
            adapter = popularAnimesAdapter
        }
    }

    private fun initViewModelObservers() {
        viewModel.apply {
            popularAnimes.observe(viewLifecycleOwner, { popularAnimes ->
                popularAnimesAdapter.updateAnimes(popularAnimes)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}