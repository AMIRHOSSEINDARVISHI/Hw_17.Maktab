package code.with.cal.moviesapp.data.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//object NetworkManager {
//    private var retrofit: Retrofit? = null
//    fun getRetrofitClient(mContext: Context?): MovieApi? {
//        if (retrofit == null) {
//            val oktHttpClient = OkHttpClient.Builder()
//                .addInterceptor(NetworkConnectionInterceptor(mContext!!))
//            // Adding NetworkConnectionInterceptor with okHttpClientBuilder.
////            oktHttpClient.addInterceptor(logging)
//            retrofit = Retrofit.Builder()
//                .baseUrl("https://api.themoviedb.org")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(oktHttpClient.build())
//                .build()
//        }
//        return retrofit!!.create(MovieApi::class.java)
//    }
////    val service = retrofit!!.create(MovieApi::class.java)
//}
object NetworkManager {

    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service = retrofit.create(MovieApi::class.java)
}



