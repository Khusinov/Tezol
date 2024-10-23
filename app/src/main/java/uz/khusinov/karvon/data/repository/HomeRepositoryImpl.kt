package uz.khusinov.karvon.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.khusinov.karvon.data.remote.ApiService
import uz.khusinov.karvon.domain.model.AdsResponse
import uz.khusinov.karvon.domain.repository.HomeRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject
import uz.khusinov.marjonamarketcourier2.utills.userMessage

class HomeRepositoryImpl(
    private val apiService: ApiService,
) : HomeRepository {

    override fun getAds(): Flow<UiStateObject<AdsResponse>> = flow {
        emit(UiStateObject.LOADING)
        try {
            val response = apiService.getAds()
            if (response.success) {
                emit(UiStateObject.SUCCESS(response.data))
            } else {
                emit(UiStateObject.ERROR(response.error.message))
            }
        } catch (e: Exception) {
            Log.d("HomeRepositoryImpl", "getAds: ${e.userMessage()}")
            emit(UiStateObject.ERROR(e.userMessage()))
        }
    }
}