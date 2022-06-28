package com.spell.machinetestwr.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spell.machinetestwr.localdatabase.dao.UserDetailsDao
import com.spell.machinetestwr.localdatabase.typeconverters.AddressConverter
import com.spell.machinetestwr.localdatabase.typeconverters.CompanyConverter
import com.spell.machinetestwr.remotemodels.UserDetailsModel

@Database(entities = [UserDetailsModel::class], version = 1, exportSchema = false)
@TypeConverters(AddressConverter::class,CompanyConverter::class)
abstract class UserDetailsDatabase : RoomDatabase() {
    abstract fun userDetailsDao():UserDetailsDao

}