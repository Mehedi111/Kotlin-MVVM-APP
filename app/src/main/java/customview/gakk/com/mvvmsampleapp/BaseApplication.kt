package customview.gakk.com.mvvmsampleapp

import android.app.Application
import customview.gakk.com.mvvmsampleapp.data.db.AppDatabase
import customview.gakk.com.mvvmsampleapp.data.network.ApiService
import customview.gakk.com.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import customview.gakk.com.mvvmsampleapp.data.preferences.PreferenceProvider
import customview.gakk.com.mvvmsampleapp.data.repositories.QuotesRepository
import customview.gakk.com.mvvmsampleapp.data.repositories.UserRepository
import customview.gakk.com.mvvmsampleapp.ui.auth.AuthViewModelFactory
import customview.gakk.com.mvvmsampleapp.ui.home.profile.ProfileViewModel
import customview.gakk.com.mvvmsampleapp.ui.home.profile.ProfileViewModelFactory
import customview.gakk.com.mvvmsampleapp.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


/**
 *CREATED BY MEHEDI on 12/30/2019.
 */
class BaseApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiService(instance())}
        bind() from singleton{ AppDatabase(instance())}
        bind() from singleton{ PreferenceProvider(instance())}
        bind() from singleton{ UserRepository(instance(), instance())}
        bind() from singleton{ QuotesRepository(instance(), instance(), instance())}
        bind() from provider{ AuthViewModelFactory(instance())}
        bind() from provider{ ProfileViewModelFactory(instance())}
        bind() from provider{ QuotesViewModelFactory(instance())}
    }
}