package uz.khusinov.karvon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.ConfirmRequest
import uz.khusinov.karvon.domain.model.ConfirmResponse
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject

interface AuthRepository {
    fun login(phone: String): Flow<UiStateObject<String>>

    fun confirm(confirmRequest: ConfirmRequest): Flow<UiStateObject<ConfirmResponse>>
}