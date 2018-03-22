package com.example.raghav.theguide.listing

import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

/**
 * @author raghav
 */

val listingModule: Module = applicationContext {
    factory { ListingPresenterImpl(get()) as ListingPresenter }
    factory { ListingInteractorImpl() as ListingInteractor }
}