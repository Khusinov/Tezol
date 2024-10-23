package uz.khusinov.karvon.presentation.shops

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.khusinov.karvon.data.remote.ApiService
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.model.shop.Shop
import uz.khusinov.karvon.domain.use_case.shops.ShopPagingSource
import uz.khusinov.karvon.domain.use_case.shops.ShopsUseCases
import uz.khusinov.marjonamarketcourier2.utills.UiStateList
import javax.inject.Inject

@HiltViewModel
class ShopsViewModel @Inject constructor(
    private val shopsUseCases: ShopsUseCases,
    private val apiService: ApiService,

    ) : ViewModel() {


    val shopsPaging: Flow<PagingData<Shop>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ShopPagingSource(apiService) }
    ).flow.cachedIn(viewModelScope)

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

    var shop: Shop? = null


}