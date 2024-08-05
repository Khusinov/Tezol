package uz.khusinov.karvon.domain.use_case.shops

import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.repository.ShopsRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateList

class GetProductsUseCase(
    private val repository: ShopsRepository
) {
    operator fun invoke(shopId: Int): Flow<UiStateList<Product>> {
        return repository.getShopsProducts(shopId)
    }
}