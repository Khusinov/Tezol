package uz.khusinov.karvon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.LoginRequest
import uz.khusinov.karvon.domain.model.LoginResponse
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Flow<UiStateObject<LoginResponse>>
}