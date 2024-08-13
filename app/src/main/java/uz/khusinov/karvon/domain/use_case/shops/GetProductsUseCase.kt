package uz.khusinov.karvon.domain.use_case.shops

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.repository.ShopsRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateList

class GetProductsUseCase(
    private val repository: ShopsRepository
) {
    operator fun invoke(shopId: Int): Flow<UiStateList<Product>> {
        Log.d("TAG", "invoke: use case  $shopId ")
        return repository.getShopsProducts(shopId)
    }
}