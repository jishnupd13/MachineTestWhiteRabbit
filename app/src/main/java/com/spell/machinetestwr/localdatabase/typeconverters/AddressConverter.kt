package com.spell.machinetestwr.localdatabase.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.spell.machinetestwr.remotemodels.Address

class AddressConverter {

    @TypeConverter
    fun fromAddressToString(address: Address?):String{
        return Gson().toJson(address)
    }

    @TypeConverter
    fun fromStringToAddress(address: String):Address?{
        return Gson().fromJson(address,Address::class.java)
    }
}