package com.app.dotaheroes.api

import com.app.dotaheroes.models.HeroStats
import retrofit2.Response
import retrofit2.http.GET

//service our network call

interface ApiService {
    @GET("heroStats")
    suspend fun getHeroStats(): Response<List<HeroStats>>
}