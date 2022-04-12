package code.with.cal.moviesapp.data.local

import code.with.cal.moviesapp.data.local.database.MovieDao
import code.with.cal.moviesapp.model.ResultX
import code.with.cal.moviesapp.model.Result
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    suspend fun insertMovie(movies:List<Result>){
        movieDao.insertMovieList(*movies.toTypedArray())
    }
    suspend fun insertComingSoon(movies:List<ResultX>){
        movieDao.insertComingSoonList(*movies.toTypedArray())
    }
    fun getAllComingSoon():Flow<List<ResultX>>{
        return  movieDao.getAllComingSoon()
    }

    fun getAllMovies():Flow<List<Result>>{
       return  movieDao.getAllMovies()
    }
}