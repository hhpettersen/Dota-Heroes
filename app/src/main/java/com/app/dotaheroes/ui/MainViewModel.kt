package com.app.dotaheroes.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dotaheroes.models.HeroStats
import com.app.dotaheroes.other.Resource
import com.app.dotaheroes.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _res = MutableLiveData<Resource<List<HeroStats>>>()

    val res: LiveData<Resource<List<HeroStats>>>
        get() = _res

    init {
        getHeroStats()
    }

    private fun getHeroStats() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getHeroStats().let {
            if (it.isSuccessful) {
                println(it.body()?.first()?.copy())
                _res.postValue(Resource.success(it.body()))
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}