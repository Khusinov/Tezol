package uz.khusinov.karvon.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.khusinov.karvon.domain.model.AdsResponse
import uz.khusinov.karvon.domain.use_case.home.HomeUseCases
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private val _getAdsState = MutableStateFlow<UiStateObject<AdsResponse>>(UiStateObject.EMPTY)
    val getAdsState = _getAdsState

    fun getAds() {
        homeUseCases.getAdsUseCase.invoke().onEach { result ->
            when (result) {
                is UiStateObject.LOADING -> _getAdsState.emit(UiStateObject.LOADING)

                is UiStateObject.SUCCESS -> _getAdsState.emit(UiStateObject.SUCCESS(result.data))

                is UiStateObject.ERROR -> _getAdsState.emit(UiStateObject.ERROR(result.message))

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}