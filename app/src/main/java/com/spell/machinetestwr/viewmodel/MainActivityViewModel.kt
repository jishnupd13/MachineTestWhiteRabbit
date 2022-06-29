package com.spell.machinetestwr.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spell.machinetestwr.remotemodels.UserDetailsModel
import com.spell.machinetestwr.repository.AppRepository
import com.spell.machinetestwr.repository.LocalDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: AppRepository,
    private val localRepository: LocalDatabaseRepository
) :
    ViewModel() {

    private val userDetailsModel = MutableLiveData<List<UserDetailsModel>?>()
    var userDetailsLiveData: LiveData<List<UserDetailsModel>?> = userDetailsModel

    private val userDetailsLocalModel = MutableLiveData<List<UserDetailsModel>?>()
    var userDetailsLocalLiveData: LiveData<List<UserDetailsModel>?> = userDetailsLocalModel

    private val userDetailsModelCount = MutableLiveData<Long?>()
    var userDetailsLiveDataCount: LiveData<Long?> = userDetailsModelCount

    fun fetchUserDetails() = viewModelScope.launch {

        try {
            val response = repository.fetchUserDetails()
            if (response.isSuccessful) {
                userDetailsModel.postValue(response.body())
            } else {
                userDetailsModel.postValue(null)
            }

        } catch (e: Exception) {
            userDetailsModel.postValue(null)
        }
    }

    fun insertDetails(list: List<UserDetailsModel>) = viewModelScope.launch {
        localRepository.insertAll(list)
        getAllData()
    }

    fun getAllData() = viewModelScope.launch {
        val response = localRepository.getAllData()
        userDetailsLocalModel.postValue(response)
        Log.e("insert result", "<<<<<<< ${response.size} >>>>>>>")
    }

    fun getAllDataCount() = viewModelScope.launch {
        val response = localRepository.getAllDataCount()
        userDetailsModelCount.postValue(response)
    }

}