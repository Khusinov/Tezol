package uz.khusinov.karvon.presentation.login

import uz.khusinov.karvon.domain.use_case.auth.AuthUseCases
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.khusinov.karvon.domain.model.ConfirmRequest
import uz.khusinov.karvon.domain.model.ConfirmResponse
import uz.khusinov.karvon.domain.model.LoginRequest
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _loginState = MutableSharedFlow<UiStateObject<String>>()
    val loginState = _loginState

    private val _confirmState = MutableStateFlow<UiStateObject<ConfirmResponse>>(UiStateObject.EMPTY)
    val confirmState = _confirmState

    fun login(loginRequest: LoginRequest) {
        authUseCases.loginUseCase.invoke(loginRequest).onEach { result ->

            when (result) {
                is UiStateObject.LOADING -> _loginState.emit(UiStateObject.LOADING)

                is UiStateObject.SUCCESS -> _loginState.emit(UiStateObject.SUCCESS(result.data))

                is UiStateObject.ERROR -> _loginState.emit(UiStateObject.ERROR(result.message))

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun confirm(confirmRequest: ConfirmRequest) {
        authUseCases.confirmUseCase.invoke(confirmRequest).onEach { result ->

            when (result) {
                is UiStateObject.LOADING -> _confirmState.emit(UiStateObject.LOADING)

                is UiStateObject.SUCCESS -> _confirmState.emit(UiStateObject.SUCCESS(result.data))

                is UiStateObject.ERROR -> _confirmState.emit(UiStateObject.ERROR(result.message))

                else -> {}
            }
        }.launchIn(viewModelScope)
    }


}