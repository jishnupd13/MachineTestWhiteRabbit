package com.spell.machinetestwr.localdatabase.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.spell.machinetestwr.remotemodels.Address
import com.spell.machinetestwr.remotemodels.Company

class CompanyConverter {

    @TypeConverter
    fun fromCompanyToString(company: Company?):String{
        return Gson().toJson(company)
    }

    @TypeConverter
    fun fromStringToCompany(company: String): Company? {
        return Gson().fromJson(company, Company::class.java)
    }
}