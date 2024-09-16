package uz.khusinov.karvon.domain.use_case.products


import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.repository.ProductsRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateList
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: ProductsRepository) {
    operator fun invoke(): Flow<UiStateList<Product>> {
        return repository.getProductsOnBasket()
    }
}