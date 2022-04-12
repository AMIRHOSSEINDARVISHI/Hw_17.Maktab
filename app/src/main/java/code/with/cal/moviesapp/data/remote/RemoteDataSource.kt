package code.with.cal.moviesapp.data.remote

import code.with.cal.moviesapp.data.remote.network.MovieApi

class RemoteDataSource(private val service: MovieApi) {
    suspend fun searchMovie(query:String)=service.searchMovie(query)

    suspend fun getAllMovies(page:Int)=service.getAllMovies(page)
    suspend fun getComingSoonMovies(page:Int)=service.getComingSoonMovies(page)
    suspend fun getMovie(movie_id:Int)=service.getMovie(movie_id)
}