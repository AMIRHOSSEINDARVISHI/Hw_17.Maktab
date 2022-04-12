package code.with.cal.moviesapp.ui.homefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import code.with.cal.moviesapp.data.Repository

class HomeViewModelFactory(
    private val repository: Repository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}