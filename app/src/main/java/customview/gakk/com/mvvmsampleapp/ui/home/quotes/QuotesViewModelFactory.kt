package customview.gakk.com.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import customview.gakk.com.mvvmsampleapp.data.repositories.QuotesRepository
import customview.gakk.com.mvvmsampleapp.data.repositories.UserRepository


/**
 *CREATED BY MEHEDI on 12/30/2019.
 */
@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(
    private val repository: QuotesRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}