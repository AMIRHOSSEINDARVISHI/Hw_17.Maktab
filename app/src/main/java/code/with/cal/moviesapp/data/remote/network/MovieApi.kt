package code.with.cal.moviesapp.data.remote.network

import code.with.cal.moviesapp.model.Comingsoon
import code.with.cal.moviesapp.model.MovieDetails
import code.with.cal.moviesapp.model.PopularMovies
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {
    @GET("3/search/movie")
   suspend fun searchMovie(
        @Query("query") query:String,
        @Query("api_key") api_key:String="74c4235743a63bbae73b00598eaab45a"
   ):Response<PopularMovies>

    @GET("3/movie/popular")
    suspend fun getAllMovies(
        @Query("page") page:Int,
        @Query("api_key") api_key:String="74c4235743a63bbae73b00598eaab45a"
    ) : Response<PopularMovies>

    @GET("3/movie/upcoming")
    suspend fun getComingSoonMovies(
        @Query("page") page:Int,
        @Query("api_key") api_key:String="74c4235743a63bbae73b00598eaab45a"
    ) : Response<Comingsoon>

    @GET("3/movie/{movie_id}?")
    suspend fun getMovie(
        @Path("movie_id") movie_id:Int,
        @Query("api_key") api_key:String="74c4235743a63bbae73b00598eaab45a"
    ) :Response<MovieDetails>

}