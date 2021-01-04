package com.luisfelipe.animefinder.presentation.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.luisfelipe.animefinder.presentation.details.episodes.EpisodesFragment
import com.luisfelipe.animefinder.presentation.details.overview.OverviewFragment
import com.luisfelipe.animefinder.presentation.details.extras.ExtrasFragment

class DetailsViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = TOTAL_NUMBER_OF_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OverviewFragment.getInstance()
            1 -> ExtrasFragment.getInstance()
            2 -> EpisodesFragment.getInstance()
            else -> OverviewFragment.getInstance()
        }
    }

    private companion object {
        const val TOTAL_NUMBER_OF_TABS = 3
    }
}