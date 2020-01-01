package customview.gakk.com.mvvmsampleapp.ui.home.quotes

import com.xwray.groupie.databinding.BindableItem
import customview.gakk.com.mvvmsampleapp.R
import customview.gakk.com.mvvmsampleapp.data.db.entities.Quote
import customview.gakk.com.mvvmsampleapp.databinding.ItemQuotesBinding


/**
 *CREATED BY MEHEDI on 1/1/2020.
 */
class QuoteItem(
    private val quote: Quote
): BindableItem<ItemQuotesBinding>() {

    override fun getLayout() = R.layout.item_quotes
    override fun bind(viewBinding: ItemQuotesBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}