package com.example.marvelapiapp.ui

import android.view.View
import com.example.marvelapiapp.data.model.SuperHero

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, superHero: SuperHero)
}