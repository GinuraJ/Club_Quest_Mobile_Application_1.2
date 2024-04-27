package com.example.clubquest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LeagueDoa {

    @Query("select * from table1")
    suspend fun getAll(): List<League>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg league: League)

    @Insert
    suspend fun insertUser(league: League)

//    @Insert
//    suspend fun insertTeam(league: Teams)

    @Delete
    suspend fun deleteUser(league: League)

    @Query("delete from table1")
    suspend fun deleteAll()

//    @Query("SELECT * FROM League WHERE strLeague LIKE :queryString OR strSport LIKE :queryString OR strLeagueAlternate LIKE :queryString")
//    suspend fun search(queryString: String): List<League>



}