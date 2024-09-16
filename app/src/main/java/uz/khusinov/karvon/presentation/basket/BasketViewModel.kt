package uz.khusinov.karvon.presentation.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.use_case.products.ProductsUseCases
import uz.khusinov.marjonamarketcourier2.utills.UiStateList
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(
    private val productUseCase: ProductsUseCases
) : ViewModel() {

    fun insertProductToBasket(products: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.insertProductUseCase.invoke(products)
        }
    }

    fun updateProduct(products: Product) =
        viewModelScope.launch {
            productUseCase.updateProductUseCase.invoke(products)
        }

    fun deleteProduct(products: Product) =
        viewModelScope.launch {
            productUseCase.removeProductUseCase.invoke(products)
        }

    fun deleteAllProducts() {
        viewModelScope.launch {
            productUseCase.deleteAllProductUseCase.invoke()
        }
    }


    private val _basketProductsState = MutableStateFlow<UiStateList<Product>>(UiStateList.EMPTY)
    val basketProductsState = _basketProductsState

    fun getBasketProducts() {
        productUseCase.getProductsUseCase.invoke().onEach { result ->
            when (result) {
                is UiStateList.LOADING -> _basketProductsState.emit(UiStateList.LOADING)

                is UiStateList.SUCCESS -> _basketProductsState.emit(UiStateList.SUCCESS(result.data))

                is UiStateList.ERROR -> _basketProductsState.emit(UiStateList.ERROR(result.message))

                else -> {
                }
            }
        }.launchIn(viewModelScope)
    }

}
