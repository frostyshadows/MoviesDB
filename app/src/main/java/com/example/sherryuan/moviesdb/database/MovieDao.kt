package com.example.sherryuan.moviesdb.database

import android.arch.persistence.room.*
import com.example.sherryuan.moviesdb.models.Movie
import io.reactivex.Flowable


@Dao
interface MovieDao {

    // find by ID query
    // retrieve the Movie with the correct ID from the database
    @Query("SELECT * FROM Movie WHERE id = :arg0")
    abstract fun findById(id: Int?): Flowable<List<Movie>>

    // insert a Movie into the database
    // if the ID conflicts with a previously inserted Movie, replace it
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    // delete a Movie from the database
    @Delete
    fun delete(movie: Movie)

    // retrieve all Movies from the database
    @Query("SELECT * FROM Movie")
    fun loadAll(): Flowable<List<Movie>>
}