package com.spell.machinetestwr.repository

import com.spell.machinetestwr.localdatabase.dao.UserDetailsDao
import com.spell.machinetestwr.remotemodels.UserDetailsModel

class LocalDatabaseRepository(private val dao: UserDetailsDao) {

    suspend fun insertAll(list: List<UserDetailsModel>) = dao.insertAll(list)

    suspend fun getAllData():List<UserDetailsModel> = dao.getAllData()
}