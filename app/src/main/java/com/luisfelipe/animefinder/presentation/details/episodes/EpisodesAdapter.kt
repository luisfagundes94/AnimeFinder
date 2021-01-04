package com.luisfelipe.animefinder.presentation.details.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.domain.model.Episode

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {

    private val episodes = mutableListOf<Episode>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.episode_item_layout, parent, false)
        return EpisodesViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    fun updateEpisodes(episodes: List<Episode>) {
        if (this.episodes.isNotEmpty()) this.episodes.clear()

        this.episodes.addAll(episodes)
        notifyDataSetChanged()
    }

    override fun getItemCount() = episodes.size

    inner class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val episodeNumber: TextView = itemView.findViewById(R.id.episode_number)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val releaseDate: TextView = itemView.findViewById(R.id.release_date)

        fun bind(episode: Episode) {
            episodeNumber.text = episode.episode_id.toString()
            title.text = episode.title
            releaseDate.text = episode.getFormattedReleaseDate()
        }
    }
}