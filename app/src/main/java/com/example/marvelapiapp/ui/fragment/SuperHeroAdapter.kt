package com.example.marvelapiapp.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapiapp.R
import com.example.marvelapiapp.data.model.SuperHero
import com.example.marvelapiapp.databinding.RecyclerviewSuperheroBinding
import com.example.marvelapiapp.ui.RecyclerViewClickListener

class SuperHeroAdapter(
    private val superHeroes: List<SuperHero>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {

    inner class SuperHeroViewHolder(
        val recyclerviewSuperheroBinding: RecyclerviewSuperheroBinding
    ) : RecyclerView.ViewHolder(recyclerviewSuperheroBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuperHeroViewHolder = SuperHeroViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_superhero,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.recyclerviewSuperheroBinding.superhero = superHeroes[position]
        holder.recyclerviewSuperheroBinding.buttonSuperHeroPage.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewSuperheroBinding.buttonSuperHeroPage,superHeroes[position])
        }
    }

    override fun getItemCount() : Int = superHeroes.size


}