package com.example.marvelapiapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapiapp.data.model.SuperHero
import com.example.marvelapiapp.data.model.SuperHeroDetail
import com.example.marvelapiapp.data.repositories.ApiException
import com.example.marvelapiapp.data.repositories.SuperHeroRepository
import com.example.marvelapiapp.utils.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(
    private val mSuperHeroRepository: SuperHeroRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _errorLiveData = MutableLiveData<String>()
    val errorLivedata: LiveData<String> get() = _errorLiveData

    private val _superHeroesData = MutableLiveData<List<SuperHero>>()
    val superHeroesData: LiveData<List<SuperHero>> get() = _superHeroesData

    private val _superHeroDetail = MutableLiveData<SuperHeroDetail>()
    val superHeroDetail: LiveData<SuperHeroDetail> get() = _superHeroDetail

    private val _superHeroId = MutableLiveData<Int>()
    val superHeroId: LiveData<Int> get() = _superHeroId

    fun setSuperHeroId(superHeroID: Int){
        _superHeroId.value = superHeroID
        getSuperHeroDetail(superHeroID)
    }

    fun getSuperHeroes(mLimit: Int) {
        getSuperHeroesFromRepository(mLimit)
    }

    fun getSuperHeroDetail(superHeroID: Int) {
        getSuperHeroDetailsFromRepository(superHeroID)
    }

    private fun getSuperHeroesFromRepository(mLimit: Int) {
        job = Coroutines.io {
            try {
                _superHeroesData.postValue(mSuperHeroRepository.getSuperHeroList(mLimit))
            } catch (e: ApiException) {
                _errorLiveData.postValue(e.message)
            }
        }

    }

    private fun getSuperHeroDetailsFromRepository(mSuperHeroID: Int) {
        job = Coroutines.io {
            try {
                _superHeroDetail.postValue(mSuperHeroRepository.getSuperHeroDetails(mSuperHeroID))
            } catch (e: ApiException) {
                _errorLiveData.postValue(e.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}