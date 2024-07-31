package uz.khusinov.karvon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.model.shop.Shop
import uz.khusinov.marjonamarketcourier2.utills.UiStateList

interface ShopsRepository {
    fun getShops(): Flow<UiStateList<Shop>>
    fun getShopsProducts(shopId:Int): Flow<UiStateList<Product>>
}