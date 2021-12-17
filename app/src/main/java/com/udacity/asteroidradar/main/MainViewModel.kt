package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.AsteroidApiService
import com.udacity.asteroidradar.api.AsteroidApiStatus
import com.udacity.asteroidradar.api.AsteroidsApi
import com.udacity.asteroidradar.api.AsteroidsApi.retrofitService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _status =  MutableLiveData<AsteroidApiStatus>()

    val status: LiveData<AsteroidApiStatus>
    get() = _status

    private val _asteroidList = MutableLiveData<List<Asteroid>>()

    val asteroidList: LiveData<List<Asteroid>>
    get() = _asteroidList

    private fun getAsteroidList(){
        viewModelScope.launch {
            _status.value = AsteroidApiStatus.LOADING
            try{
                _asteroidList.value = AsteroidsApi.retrofitService.getAsteroidList()
            }
        }
    }
}