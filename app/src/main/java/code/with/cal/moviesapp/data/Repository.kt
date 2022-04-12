package code.with.cal.moviesapp.data

import code.with.cal.moviesapp.data.local.LocalDataSource
import code.with.cal.moviesapp.data.remote.RemoteDataSource
import code.with.cal.moviesapp.model.ResultX
import code.with.cal.moviesapp.model.Result
import kotlinx.coroutines.flow.Flow

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun searchMovie(query: String) = remoteDataSource.searchMovie(query)
    suspend fun insertMoviesToLocal() =
        localDataSource.insertMovie(remoteDataSource.getAllMovies(1).body()!!.results)

    suspend fun insertComingSoon() =
        localDataSource.insertComingSoon(remoteDataSource.getComingSoonMovies(1).body()!!.results)

    fun getComingSoonFromLocal(): Flow<List<ResultX>> {
        return localDataSource.getAllComingSoon()
    }

    fun getFromLocal(): Flow<List<Result>> {
        return localDataSource.getAllMovies()
    }

    suspend fun getMovie(movie_id: Int) = remoteDataSource.getMovie(movie_id)

}