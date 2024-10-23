package uz.khusinov.karvon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.AdsResponse
import uz.khusinov.karvon.domain.model.Data
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject

interface HomeRepository {
    fun getAds(): Flow<UiStateObject<AdsResponse>>

    fun getNewProducts(): Flow<UiStateObject<Data<Product>>>

    fun getTopProducts(): Flow<UiStateObject<Data<Product>>>

    fun getMostSoldProducts(): Flow<UiStateObject<Data<Product>>>
}