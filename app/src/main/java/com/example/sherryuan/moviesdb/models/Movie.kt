package com.example.sherryuan.moviesdb.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Movie(
        @PrimaryKey
        var id: Int = 0,
        var title: String = "",
        var description: String = "",
        var rating: Int = 0
)