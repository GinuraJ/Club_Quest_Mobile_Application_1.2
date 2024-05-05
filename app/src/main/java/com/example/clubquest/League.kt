package com.example.clubquest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table1")
data class League(
    @PrimaryKey(autoGenerate = true) var Id: Int = 0,
    val leagueId: String?,
    val strLeague: String?,
    val strSport: String?,
    val strLeagueAlternate:String?,
//    val strLogo:String?

)