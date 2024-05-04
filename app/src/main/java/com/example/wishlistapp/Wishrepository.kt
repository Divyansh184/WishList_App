package com.example.wishlistapp

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {


    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }
    fun getWish():Flow<List<Wish>> =wishDao.getAllWish()

    fun getWishById(id:Int):Flow<Wish>{
        return wishDao.getaWish(id)
    }
    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }
    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }
}