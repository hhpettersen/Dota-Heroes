package com.app.dotaheroes.repository

import com.app.dotaheroes.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getHeroStats() = apiHelper.getHeroStats()
}