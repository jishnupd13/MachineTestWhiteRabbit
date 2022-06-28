package com.spell.machinetestwr.remotenetwork

import com.spell.machinetestwr.remotemodels.UserDetailsModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v2/5d565297300000680030a986")
    suspend fun fetchUserDetails(): Response<List<UserDetailsModel>>
}