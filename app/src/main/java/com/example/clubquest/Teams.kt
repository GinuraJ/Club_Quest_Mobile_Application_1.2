package com.example.clubquest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table2")
data class Teams(
    @PrimaryKey(autoGenerate = true) var Id: Int = 0,
    val idTeam: String?,
    val Name: String?,
    val strTeamShort: String?,
    val strAlternate: String?,
    val intFormedYear: String?,
    val strLeague: String?,
    val idLeague: String?,
    val strStadium: String?,
    val strKeywords: String?,
    val strStadiumThumb: String?,
    val strStadiumLocation: String?,
    val intStadiumCapacity: String?,
    val strWebsite: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?

)
