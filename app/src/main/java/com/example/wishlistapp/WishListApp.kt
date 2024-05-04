package com.example.wishlistapp

import android.app.Application
import eu.tutorials.mywishlistapp.Graph

class WishListApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}