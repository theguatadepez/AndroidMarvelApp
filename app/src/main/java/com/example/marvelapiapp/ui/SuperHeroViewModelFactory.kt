package com.example.marvelapiapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelapiapp.data.repositories.SuperHeroRepository
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

//@Suppress("UNCHECKED_CAST")
//@Singleton
//@Component.Factory
//class SuperHeroViewModelFactory @Inject constructor(
//    private val repository: SuperHeroRepository
//) : ViewModelProvider.NewInstanceFactory(){
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return SuperHeroViewModel(repository) as T
//    }
//
//}
