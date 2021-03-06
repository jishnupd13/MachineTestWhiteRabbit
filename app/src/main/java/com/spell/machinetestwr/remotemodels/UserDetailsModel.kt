package com.spell.machinetestwr.remotemodels


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import com.spell.machinetestwr.localdatabase.typeconverters.AddressConverter
import com.spell.machinetestwr.localdatabase.typeconverters.CompanyConverter
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "userDetails")
data class UserDetailsModel(


    @SerializedName("address")
    @TypeConverters(AddressConverter::class)
    var address: Address? = null,

    @SerializedName("company")
    @TypeConverters(CompanyConverter::class)
    var company: Company? = null,

    @SerializedName("email")
    var email: String? = "",

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int? = 0,

    @SerializedName("name")
    var name: String? = "",
    @SerializedName("phone")
    var phone: String? = "",
    @SerializedName("profile_image")
    var profileImage: String? = "",
    @SerializedName("username")
    var username: String? = "",
    @SerializedName("website")
    var website: String? = ""
):Parcelable

@Parcelize
data class Address(
    @SerializedName("city")
    var city: String? = "",
    @SerializedName("geo")
    var geo: Geo? = null,
    @SerializedName("street")
    var street: String? = "",
    @SerializedName("suite")
    var suite: String? = "",
    @SerializedName("zipcode")
    var zipcode: String? = ""
):Parcelable

@Parcelize
data class Company(
    @SerializedName("bs")
    var bs: String? = "",
    @SerializedName("catchPhrase")
    var catchPhrase: String? = "",
    @SerializedName("name")
    var name: String? = ""
):Parcelable

@Parcelize
data class Geo(
    @SerializedName("lat")
    var lat: String? = "",
    @SerializedName("lng")
    var lng: String? = ""
):Parcelable