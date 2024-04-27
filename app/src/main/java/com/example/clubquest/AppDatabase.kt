package com.example.clubquest

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [League::class], version=1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun leagueDoa(): LeagueDoa
}
