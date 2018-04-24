package com.example.sherryuan.moviesdb.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.sherryuan.moviesdb.models.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MovieDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}