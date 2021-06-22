package com.victorasj.o2oprueba.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import basicDiffUtil
import com.victorasj.domain.Beer
import com.victorasj.o2oprueba.R
import com.victorasj.o2oprueba.databinding.BeerViewBinding
import inflate
import loadUrl

class BeersAdapter(val listener: (Beer) -> Unit) : RecyclerView.Adapter<BeersAdapter.ViewHolder>() {

    var beers : List<Beer> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.beer_view, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beers[position]
        holder.bind(beer)
        holder.itemView.setOnClickListener { listener(beer) }
    }

    override fun getItemCount(): Int = beers.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = BeerViewBinding.bind(view)
        fun bind(beer : Beer) = with(binding){
            beerName.text = beer.name
            beer.image_url?.let { beerImage.loadUrl(it) }
        }
    }
}

