package code.with.cal.moviesapp.ui.detailsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import code.with.cal.moviesapp.data.Repository
import code.with.cal.moviesapp.model.MovieDetails
import kotlinx.coroutines.launch

class DetailsFragmentViewModel(private val repository: Repository):
    ViewModel() {
    val movieList = MutableLiveData<MovieDetails>()
    fun getMovie(movie_id:Int){
        viewModelScope.launch {
          val response=  repository.getMovie(movie_id)
            if (response.isSuccessful){
                movieList.postValue(response.body())
            }
        }
    }
}