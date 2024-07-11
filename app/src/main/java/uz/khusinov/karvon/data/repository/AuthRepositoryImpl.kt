package uz.khusinov.marjonamarketcourier2.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.khusinov.karvon.data.remote.ApiService
import uz.khusinov.karvon.domain.model.LoginRequest
import uz.khusinov.karvon.domain.model.LoginResponse
import uz.khusinov.karvon.domain.repository.AuthRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject
import uz.khusinov.marjonamarketcourier2.utills.userMessage

class AuthRepositoryImpl(
    private val apiService: ApiService,
) : AuthRepository {

    override fun login(loginRequest: LoginRequest): Flow<UiStateObject<LoginResponse>> = flow {
        emit(UiStateObject.LOADING)
        try {
            val response = apiService.login(loginRequest)
            if (response.success) {
                Log.d("TAG", "login: impl $response ")
                emit(UiStateObject.SUCCESS(response.data))
            } else
                emit(UiStateObject.ERROR(response.error.message))
        } catch (e: Exception) {
            emit(UiStateObject.ERROR(e.userMessage()))
        }
    }
}