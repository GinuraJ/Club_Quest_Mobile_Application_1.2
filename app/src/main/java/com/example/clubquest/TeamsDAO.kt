package com.example.clubquest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.jar.Attributes.Name

@Dao
interface TeamsDAO {

    @Insert
    suspend fun insertTeam(league: Teams)

    @Query("delete from table2")
    suspend fun deleteAll()

//    @Query("SELECT idTeam, Name FROM table2 WHERE Name = :name")
//    fun getIdTeamById(name: String):List<Teams>

    @Query("SELECT * FROM table2 WHERE LOWER(strLeague) LIKE :queryString OR LOWER(Name) LIKE :queryString OR LOWER(strAlternate) LIKE :queryString")
    suspend fun search(queryString: String): List<Teams>

}