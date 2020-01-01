package customview.gakk.com.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

import customview.gakk.com.mvvmsampleapp.R
import customview.gakk.com.mvvmsampleapp.data.db.entities.Quote
import customview.gakk.com.mvvmsampleapp.util.Coroutines
import customview.gakk.com.mvvmsampleapp.util.hide
import customview.gakk.com.mvvmsampleapp.util.show
import customview.gakk.com.mvvmsampleapp.util.toast
import kotlinx.android.synthetic.main.quotes_fragment.*
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.util.*

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        bindhUI()
    }

    private fun bindhUI() = Coroutines.main {
        progress_bar.show()
        viewModel.quotes.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerview(it.toQuoteItem())
        })


    }

    private fun initRecyclerview(toQuoteItem: List<QuoteItem>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toQuoteItem)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }


    private fun List<Quote>.toQuoteItem(): List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }

}
