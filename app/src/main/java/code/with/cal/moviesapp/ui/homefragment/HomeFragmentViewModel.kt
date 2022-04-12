package code.with.cal.moviesapp.ui.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import code.with.cal.moviesapp.data.Repository
import code.with.cal.moviesapp.model.ResultX
import code.with.cal.moviesapp.model.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeFragmentViewModel(private val repository: Repository) : ViewModel() {

    val searchMovie = MutableLiveData<List<Result>>()
    var job: Job? = null

    private val _listMovies = MutableStateFlow<List<Result>>(emptyList())
    val listMovies: StateFlow<List<Result>> = _listMovies

    private val _listComingSoon = MutableStateFlow<List<ResultX>>(emptyList())
    val listComingSoon: StateFlow<List<ResultX>> = _listComingSoon


    suspend fun insertComingSoon() {
        repository.insertComingSoon()
    }

    suspend fun insertMovies() {
        repository.insertMoviesToLocal()
    }

    fun getComingSoonFromLocal() {
        viewModelScope.launch {
            repository.getComingSoonFromLocal().collect {
                _listComingSoon.emit(it)
            }
        }
    }

    fun getFromLocal() {
        viewModelScope.launch {
            repository.getFromLocal().collect {
                _listMovies.emit(it)
            }
        }

    }

    fun searchMovie(query: String) {
        viewModelScope.launch {
            val response = repository.searchMovie(query)
            withContext(Main) {
                if (response.isSuccessful) {
                    searchMovie.postValue(response.body()!!.results)
                }
            }

        }


    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}