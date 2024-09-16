package uz.khusinov.karvon.presentation.shops

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.model.shop.Shop
import uz.khusinov.karvon.domain.use_case.shops.ShopsUseCases
import uz.khusinov.marjonamarketcourier2.utills.UiStateList
import javax.inject.Inject

@HiltViewModel
class ShopsViewModel @Inject constructor(
    private val shopsUseCases: ShopsUseCases
) : ViewModel() {

    private val _shopsState = MutableSharedFlow<UiStateList<Shop>>()
    val shopsState = _shopsState


    fun getShops() {
        shopsUseCases.getShopsUseCase.invoke().onEach { result ->

            when (result) {
                is UiStateList.LOADING -> _shopsState.emit(UiStateList.LOADING)

                is UiStateList.SUCCESS -> _shopsState.emit(UiStateList.SUCCESS(result.data))

                is UiStateList.ERROR -> _shopsState.emit(UiStateList.ERROR(result.message))

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private val _productState = MutableSharedFlow<UiStateList<Product>>()
    val productState = _productState


    fun getProducts(shopId: Int) {
        shopsUseCases.getProductsUseCase.invoke(shopId).onEach { result ->

            when (result) {
                is UiStateList.LOADING -> _productState.emit(UiStateList.LOADING)

                is UiStateList.SUCCESS -> _productState.emit(UiStateList.SUCCESS(result.data))

                is UiStateList.ERROR -> _productState.emit(UiStateList.ERROR(result.message))

                else -> {}
            }
        }.launchIn(viewModelScope)
    }




}