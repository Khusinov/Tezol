package uz.khusinov.karvon.domain.use_case.products

import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.repository.ProductsRepository
import javax.inject.Inject

class InsertProductUseCase @Inject constructor(private val repository: ProductsRepository) {
    suspend operator fun invoke(address: Product) {
        repository.insertProduct(address)
    }
}