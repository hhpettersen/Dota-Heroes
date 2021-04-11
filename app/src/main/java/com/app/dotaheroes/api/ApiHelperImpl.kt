package com.app.dotaheroes.api

import com.app.dotaheroes.models.HeroStats
import retrofit2.Response
import javax.inject.Inject
/*
class that implements ApiHelper to provide functionality to ApiHelper functions.
All functions will be suspended, so all network calls are on background thread instead of main thread.

we inject ApiService in the constructor so we dont have to create a new instance of ApiService, instead
its passed as dependency, this makes it possible to have only one instance of ApiService throughout the whole app lifecycle
*/
class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {
    override suspend fun getHeroStats(): Response<List<HeroStats>> = apiService.getHeroStats()
}