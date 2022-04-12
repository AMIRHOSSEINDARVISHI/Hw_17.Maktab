package code.with.cal.moviesapp.Dp

import android.app.Application
import code.with.cal.moviesapp.data.Repository
import code.with.cal.moviesapp.data.local.LocalDataSource
import code.with.cal.moviesapp.data.local.database.AppDataBase
import code.with.cal.moviesapp.data.remote.RemoteDataSource
import code.with.cal.moviesapp.data.remote.network.NetworkManager

class ServiceLocator(application: Application) {
    val remoteDataSource = RemoteDataSource(NetworkManager.service)
    val localDataSource= LocalDataSource(AppDataBase.getDatabase(application).movieDao())
    val repository = Repository(remoteDataSource,localDataSource)
}