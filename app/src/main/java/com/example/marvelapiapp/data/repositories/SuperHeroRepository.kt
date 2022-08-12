package com.example.marvelapiapp.data.repositories

import com.example.marvelapiapp.data.model.SuperHero
import com.example.marvelapiapp.data.model.SuperHeroDetail
import com.example.marvelapiapp.data.network.SuperHeroAPI
import javax.inject.Inject

class SuperHeroRepository @Inject constructor(
    private val mSuperHeroAPI: SuperHeroAPI
) : SafeAPIRequest(){

    suspend fun getSuperHeroList(mLimit:Int): List<SuperHero> {
        var mSuperHero: SuperHero
        val mSuperHeroList : MutableList<SuperHero> = ArrayList()
        val mSuperHeroResponse = apiRequest { mSuperHeroAPI.getSuperHeroList(mLimit)}

        mSuperHeroResponse.data.results.forEach {
            mSuperHero = SuperHero(it.id,
                it.name,
                it.thumbnail.path+"."+it.thumbnail.extension
            )
            mSuperHeroList.add(mSuperHero)
        }
        return mSuperHeroList
    }

    suspend fun getSuperHeroDetails(superHeroID: Int): SuperHeroDetail {
        val mSuperHeroResponse = apiRequest {mSuperHeroAPI.getSuperHeroDetail(superHeroID = superHeroID.toString())}
        var mSuperHeroDetail: SuperHeroDetail
        val mResult = mSuperHeroResponse.data.results[0]
        mResult.let {
            mSuperHeroDetail = SuperHeroDetail(it.id,
                it.name,
                it.description,
                it.comics.available,
                it.events.available,
                it.series.available,
                it.stories.available,
                it.thumbnail.path+"."+it.thumbnail.extension,
                it.urls
            )
        }
        return mSuperHeroDetail
    }

}