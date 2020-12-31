package com.luisfelipe.animefinder.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.animefinder.R
import com.luisfelipe.animefinder.domain.model.Anime
import com.luisfelipe.animefinder.utils.load

class AnimeAdapter: RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    private val animes = mutableListOf<Anime>()

    fun updateAnimes(animes: List<Anime>) {
        if (this.animes.isNotEmpty())
            this.animes.clear()

        this.animes.addAll(animes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animes[position])
    }

    override fun getItemCount() = animes.size

    inner class AnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.image)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val episodes: TextView = itemView.findViewById(R.id.episodes)
        private val score: TextView = itemView.findViewById(R.id.score)

        fun bind(anime: Anime) {
            image.load(anime.imageUrl)
            title.text = anime.title
            episodes.text = anime.episodes.toString()
            score.text = anime.score.toString()
        }
    }
}