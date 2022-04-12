package code.with.cal.moviesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Result(
    val adult: Boolean,
    @ColumnInfo(name="backdrop_path")
    val backdrop_path: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name="poster_path")
    val poster_path: String,
    val release_date: String,
    @ColumnInfo(name = "title")
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)