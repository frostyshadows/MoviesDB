package com.example.sherryuan.moviesdb

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.sherryuan.moviesdb.database.MovieDao
import com.example.sherryuan.moviesdb.database.MovieDb
import com.example.sherryuan.moviesdb.models.Movie
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class MovieDbTest {
    lateinit private var movieDao: MovieDao
    lateinit private var testDatabase: MovieDb

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        testDatabase = Room.inMemoryDatabaseBuilder(context, MovieDb::class.java).build()
        movieDao = testDatabase.movieDao()
    }

    @After
    fun closeDb() {
        testDatabase.close()
    }

    @Test
    fun testMovieDaoQueries() {
        val movie1 = Movie(1, "Batman Begins", "first movie", 3)
        movieDao.insert(movie1)

        val movie2 = Movie(2, "The Dark Knight", "second movie", 5)
        movieDao.insert(movie2)

        val movie3 = Movie(3, "The Dark Knight Rises", "third movie", 4)
        movieDao.insert(movie3)

        // test that we can find the first Movie
        val movieById1 = movieDao.findById(1).blockingFirst()
        Assert.assertEquals(movieById1[0].title, movie1.title)
        Assert.assertEquals(movieById1[0].rating, movie1.rating)

        Assert.assertEquals(movieById1.size.toLong(), 1)

        // test that we can find the first Movie
        val movieById3 = movieDao.findById(3).blockingFirst()
        Assert.assertEquals(movieById3[0].title, movie3.title)
        Assert.assertEquals(movieById3[0].rating, movie3.rating)

        Assert.assertEquals(movieById3.size.toLong(), 1)

        // test that we can get all the Movies
        val allMovies = movieDao.loadAll().blockingFirst()
        Assert.assertEquals(allMovies[0].description, movie1.description)
        Assert.assertEquals(allMovies[1].description, movie2.description)
        Assert.assertEquals(allMovies[2].description, movie3.description)

        Assert.assertEquals(allMovies.size.toLong(), 3)
    }
}
