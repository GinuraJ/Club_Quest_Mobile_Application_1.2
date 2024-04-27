package com.example.clubquest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LeagueDoa {

    @Query("select * from league")
    suspend fun getAll(): List<League>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg league: League)

    @Insert
    suspend fun insertUser(league: League)

    @Delete
    suspend fun deleteUser(league: League)

    @Query("delete from league")
    suspend fun deleteAll()

//    @Query("SELECT * FROM League WHERE strLeague LIKE :queryString OR strSport LIKE :queryString OR strLeagueAlternate LIKE :queryString")
//    suspend fun search(queryString: String): List<League>

    @Query("SELECT * FROM League WHERE LOWER(strLeague) LIKE :queryString OR LOWER(strSport) LIKE :queryString OR LOWER(strLeagueAlternate) LIKE :queryString")
    suspend fun search(queryString: String): List<League>

}