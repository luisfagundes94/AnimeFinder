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

    @Named("popularAnimesAdapter")
    @Inject
    lateinit var popularAnimesAdapter: AnimeAdapter

    @Named("latestAnimesAdapter")
    @Inject
    lateinit var latestAnimesAdapter: AnimeAdapter

    @Named("upcomingAnimesAdapter")
    @Inject
    lateinit var upcomingAnimesAdapter: AnimeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)


        initPopularAnimesRecyclerView()
        initLatestAnimesRecyclerView()
        initUpcomingAnimesRecyclerView()
        initViewModelObservers()

        viewModel.getPopularAnimes()
        viewModel.getLatestAnimes()
        viewModel.getUpcomingAnimes()
    }

    override fun onResume() {
        super.onResume()
        startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        stopShimmerAnimation()
    }

    private fun startShimmerAnimation() {
        binding.shimmerViewContainer.startShimmer()
        binding.shimmerViewContainer2.startShimmer()
        binding.shimmerViewContainer3.startShimmer()
    }

    private fun stopShimmerAnimation() {
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer2.stopShimmer()
        binding.shimmerViewContainer3.stopShimmer()
    }

    private fun initPopularAnimesRecyclerView() {
        binding.recyclerViewPopular.apply {
            setHasFixedSize(true)
            layoutManager = horizontalRecyclerViewLayout()
            adapter = popularAnimesAdapter
        }
    }

    private fun initLatestAnimesRecyclerView() {
        binding.recyclerViewReleases.apply {
            setHasFixedSize(true)
            layoutManager = horizontalRecyclerViewLayout()
            adapter = latestAnimesAdapter
        }
    }

    private fun initUpcomingAnimesRecyclerView() {
        binding.recyclerViewUpcoming.apply {
            setHasFixedSize(true)
            layoutManager = horizontalRecyclerViewLayout()
            adapter = upcomingAnimesAdapter
        }
    }

    private fun initViewModelObservers() {
        viewModel.apply {
            popularAnimes.observe(viewLifecycleOwner, { popularAnimes ->
                popularAnimesAdapter.updateAnimes(popularAnimes)
            })
            latestAnimes.observe(viewLifecycleOwner, { latestAnimes ->
                latestAnimesAdapter.updateAnimes(latestAnimes)
            })
            upcomingAnimes.observe(viewLifecycleOwner, { upcomingAnimes ->
                upcomingAnimesAdapter.updateAnimes(upcomingAnimes)
            })
            shimmerAnimation.observe(viewLifecycleOwner, { isEnabled ->
                when (isEnabled) {
                    true -> startShimmerAnimation()
                    false -> stopShimmerAnimation()
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}