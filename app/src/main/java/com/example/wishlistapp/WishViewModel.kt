package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.mywishlistapp.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository=Graph.wishRepository
):ViewModel() {
    var titleState by mutableStateOf("")
    var descState by mutableStateOf("")
    fun titleChange(newstr: String) {
        titleState = newstr
    }

    fun descChange(newstr: String) {
        descState = newstr
    }

    lateinit var getAllWish: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWish = wishRepository.getWish()
        }
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish = wish)
        }
    }

    fun getAWishById(id: Int): Flow<Wish> {
        return wishRepository.getWishById(id)
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish = wish)
        }
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish = wish)
            getAllWish = wishRepository.getWish()
        }

    }
}