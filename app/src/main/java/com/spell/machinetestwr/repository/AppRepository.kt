package com.spell.machinetestwr.repository

import com.spell.machinetestwr.remotenetwork.ApiService

class AppRepository(private val apiService: ApiService) {

    suspend fun fetchUserDetails() = apiService.fetchUserDetails()
}