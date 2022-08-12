package com.example.marvelapiapp

import com.example.marvelapiapp.data.model.SuperHero
import com.example.marvelapiapp.data.model.SuperHeroDetail
import com.example.marvelapiapp.data.model.Url

/**
 * Dummy Data for Unit Test.
 * */

fun getDummySuperHeroDetail() : SuperHeroDetail{
    val mUrlList = ArrayList<Url>()
    mUrlList.add(Url("detail","http://marvel.com/comics/characters/1011198/agents_of_atlas?utm_campaign=apiRef&utm_source=41eace45044188b74bda4e5e2178557a"))
    mUrlList.add(Url("wiki","http://marvel.com/universe/Agents_of_Atlas?utm_campaign=apiRef&utm_source=41eace45044188b74bda4e5e2178557a"))
    mUrlList.add(Url("comiclink","http://marvel.com/comics/characters/1011198/agents_of_atlas?utm_campaign=apiRef&utm_source=41eace45044188b74bda4e5e2178557a"))

    return SuperHeroDetail(1011198,"Agents of Atlas","",45,1,13,52,"http://i.annihil.us/u/prod/marvel/i/mg/9/a0/4ce18a834b7f5.jpg",mUrlList)
}

fun getDummySuperHero(id: Int, name: String, image: String) : SuperHero {
    return SuperHero(id,name,image)
}

fun getDummySuperHeroList(): List<SuperHero> {

    val mSuperHeroList = ArrayList<SuperHero>()
    mSuperHeroList.add(getDummySuperHero(1011334,"3-D Man","http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"))
    mSuperHeroList.add(getDummySuperHero(1017100,"A-Bomb (HAS)","http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"))
    return mSuperHeroList
}

fun getWrongDummySuperHeroList(): List<SuperHero> {

    val mSuperHeroList = ArrayList<SuperHero>()
    mSuperHeroList.add(getDummySuperHero(1011198,"Agents of Atlas","http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"))
    mSuperHeroList.add(getDummySuperHero(1017100,"A-Bomb (HAS)","http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"))
    return mSuperHeroList
}

