package code.with.cal.moviesapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import code.with.cal.moviesapp.model.ResultX
import code.with.cal.moviesapp.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieList(vararg result:Result)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComingSoonList(vararg result: ResultX)

    @Query("SELECT * FROM movie")
    fun getAllComingSoon(): Flow<List<ResultX>>

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<Result>>
}