package customview.gakk.com.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import customview.gakk.com.mvvmsampleapp.data.repositories.QuotesRepository
import customview.gakk.com.mvvmsampleapp.util.lazyDeferred

class QuotesViewModel(quotesRepository: QuotesRepository) : ViewModel() {

    val quotes by lazyDeferred() {
        quotesRepository.getQuotes()
    }
}
