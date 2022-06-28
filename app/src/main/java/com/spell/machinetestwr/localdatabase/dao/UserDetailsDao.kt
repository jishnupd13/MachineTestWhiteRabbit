package com.spell.machinetestwr.localdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spell.machinetestwr.remotemodels.UserDetailsModel

@Dao
interface UserDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userDetailsList: List<UserDetailsModel>)

    @Query("select * from userDetails")
    suspend fun getAllData():List<UserDetailsModel>

}