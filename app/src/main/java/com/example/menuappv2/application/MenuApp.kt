package com.example.menuappv2.application

import androidx.multidex.MultiDexApplication
import com.example.menuappv2.network.MenuRepository
import com.example.menuappv2.network.UserRepository
import com.example.menuappv2.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MenuApp: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    /**
     * Setup DI with Koin.
     */
    private fun setupKoin(){
        startKoin {
            androidLogger()
            androidContext(this@MenuApp)
            modules(listOf(viewModelModule, repositoryModule))
        }
    }

    /**
     * Setup the module for the viewmodels.
     * Is private since the viewmodels use injected dependencies.
     */
    private val viewModelModule = module {
        viewModel {
            UserViewModel(get(), get())
        }
        viewModel {
            DecideViewModel(get(),get())
        }
        viewModel {
            MenuListViewModel(get(),get())
        }
        viewModel {
            MenuDetailViewModel(get(),get())
        }
        viewModel {
            RegisterMenuViewModel(get(), get())
        }
    }

    /**
     * Setup the repository module.
     * Is public since we mock the repositories in tests.
     */
    private val repositoryModule = module {
        single<UserRepository> {
            UserRepository()
        }
        single<MenuRepository> {
            MenuRepository()
        }
    }
}