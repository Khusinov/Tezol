package uz.khusinov.karvon.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.khusinov.karvon.data.remote.ApiService
import uz.khusinov.karvon.domain.model.ConfirmRequest
import uz.khusinov.karvon.domain.model.ConfirmResponse
 import uz.khusinov.karvon.domain.repository.AuthRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject
import uz.khusinov.marjonamarketcourier2.utills.userMessage

class AuthRepositoryImpl(
    private val apiService: ApiService,
) : AuthRepository {

    override fun login(phone: String): Flow<UiStateObject<String>> = flow {
        emit(UiStateObject.LOADING)
        try {
            val response = apiService.login(phone)
            if (response.success) {
                Log.d("TAG", "login: impl $response ")
                emit(UiStateObject.SUCCESS(response.data))
            } else
                emit(UiStateObject.ERROR(response.error.message))
        } catch (e: Exception) {
            emit(UiStateObject.ERROR(e.userMessage()))
        }
    }

    override fun confirm(confirmRequest: ConfirmRequest): Flow<UiStateObject<ConfirmResponse>> =
        flow {
            emit(UiStateObject.LOADING)
            try {
                val response = apiService.confirm(confirmRequest.verify_code, confirmRequest.phone)
                if (response.success) {
                    emit(UiStateObject.SUCCESS(response.data))
                } else {
                    emit(UiStateObject.ERROR(response.message))
                }
            } catch (e: Exception) {
                emit(UiStateObject.ERROR(e.userMessage()))
            }
        }
}