package uz.khusinov.karvon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.model.shop.Shops
import uz.khusinov.marjonamarketcourier2.utills.UiStateList
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject

interface ShopsRepository {
    fun getShopsProducts(shopId: Int): Flow<UiStateList<Product>>
}