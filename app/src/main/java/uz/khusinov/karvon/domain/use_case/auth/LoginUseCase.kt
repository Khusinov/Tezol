package uz.khusinov.karvon.domain.use_case.auth

import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.repository.AuthRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject

class LoginUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke(phone: String): Flow<UiStateObject<String>> {
        /*if (phoneNumber.length != 12){
            return flow {

            }
        }*/
        return repository.login(phone)
    }
}