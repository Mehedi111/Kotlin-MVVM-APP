package customview.gakk.com.mvvmsampleapp.data.network.responses

import customview.gakk.com.mvvmsampleapp.data.db.entities.Quote


/**
 *CREATED BY MEHEDI on 1/1/2020.
 */
data class QuoteResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
) {

}