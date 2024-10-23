package uz.khusinov.karvon.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.khusinov.karvon.domain.model.AdsResponse
import uz.khusinov.karvon.domain.model.Data
import uz.khusinov.karvon.domain.model.shop.Product
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

    private val _getNewProductsState = MutableStateFlow<UiStateObject<Data<Product>>>(UiStateObject.EMPTY)
    val getNewProductsState = _getNewProductsState

    fun getNewProducts() {
        homeUseCases.getNewProductsUseCase.invoke().onEach { result ->
            when (result) {
                is UiStateObject.LOADING -> _getNewProductsState.emit(UiStateObject.LOADING)

                is UiStateObject.SUCCESS -> _getNewProductsState.emit(UiStateObject.SUCCESS(result.data))

                is UiStateObject.ERROR -> _getNewProductsState.emit(UiStateObject.ERROR(result.message))

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private val _getTopProductsState = MutableStateFlow<UiStateObject<Data<Product>>>(UiStateObject.EMPTY)
    val getTopProductsState = _getTopProductsState

    fun getTopProducts() {
        homeUseCases.getTopProductsUseCase.invoke().onEach { result ->
            when (result) {
                is UiStateObject.LOADING -> _getTopProductsState.emit(UiStateObject.LOADING)

                is UiStateObject.SUCCESS -> _getTopProductsState.emit(UiStateObject.SUCCESS(result.data))

                is UiStateObject.ERROR -> _getTopProductsState.emit(UiStateObject.ERROR(result.message))

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private val _getMostSellProductsState =
        MutableStateFlow<UiStateObject<Data<Product>>>(UiStateObject.EMPTY)
    val getMostSellProductsState = _getMostSellProductsState

    fun getMostSellProducts() {
        homeUseCases.getMostSellProductsUseCase.invoke().onEach { result ->
            when (result) {
                is UiStateObject.LOADING -> _getMostSellProductsState.emit(UiStateObject.LOADING)

                is UiStateObject.SUCCESS -> _getMostSellProductsState.emit(UiStateObject.SUCCESS(result.data))

                is UiStateObject.ERROR -> _getMostSellProductsState.emit(UiStateObject.ERROR(result.message))

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}