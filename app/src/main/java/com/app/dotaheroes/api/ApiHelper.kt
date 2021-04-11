package com.app.dotaheroes.api

import com.app.dotaheroes.models.HeroStats
import retrofit2.Response

//help ApiService to be accessed via repository

interface ApiHelper {
    suspend fun getHeroStats(): Response<List<HeroStats>>
}