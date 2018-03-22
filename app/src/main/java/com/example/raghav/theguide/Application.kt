package com.example.raghav.theguide

import android.app.Application
import com.example.raghav.theguide.listing.listingModule
import org.koin.android.ext.android.startKoin

/**
 * @author raghav
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(listingModule))
    }

}