package uz.khusinov.karvon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.AdsResponse
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject

interface HomeRepository {
    fun getAds(): Flow<UiStateObject<AdsResponse>>
}