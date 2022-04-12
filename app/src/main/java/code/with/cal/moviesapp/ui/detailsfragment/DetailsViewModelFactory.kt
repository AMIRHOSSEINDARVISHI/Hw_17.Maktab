package code.with.cal.moviesapp.ui.detailsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import code.with.cal.moviesapp.data.Repository

class DetailsViewModelFactory(
    private  val repository: Repository,
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsFragmentViewModel::class.java)) {
            return DetailsFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}