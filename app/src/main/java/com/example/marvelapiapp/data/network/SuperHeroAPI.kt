package com.example.marvelapiapp.data.network

import com.example.marvelapiapp.data.model.SuperHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

const val PRIVATE_API_KEY = "d640992090dc425e28e00931b4f9f1df6cbe4513"
const val PUBLIC_API_KEY = "41eace45044188b74bda4e5e2178557a"
const val APP_JSON = "application/json"

interface SuperHeroAPI {

    @GET("v1/public/characters")
    suspend fun getSuperHeroList(@Query("limit") limit:Int,
                                 @Query("apikey") apiKey: String = PUBLIC_API_KEY,
                                 @Query("ts") ts: String = NetworkUtils.getTimesTamp(),
                                 @Query("hash") hash: String = NetworkUtils.md5(NetworkUtils.getTimesTamp()+ PRIVATE_API_KEY+ PUBLIC_API_KEY),
                                 @Header("Content-Type") mContentType: String = APP_JSON) : Response<SuperHeroResponse>


    @GET("v1/public/characters/{superhero_id}")
    suspend fun getSuperHeroDetail(@Path(value = "superhero_id", encoded = true ) superHeroID: String,
                                   @Query("apikey") apiKey: String = PUBLIC_API_KEY,
                                   @Query("ts") ts: String = NetworkUtils.getTimesTamp(),
                                   @Query("hash") hash: String = NetworkUtils.md5(NetworkUtils.getTimesTamp()+ PRIVATE_API_KEY+ PUBLIC_API_KEY),
                                   @Header("Content-Type") mContentType: String = APP_JSON) : Response<SuperHeroResponse>



}