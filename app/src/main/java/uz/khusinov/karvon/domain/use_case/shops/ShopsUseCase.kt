package uz.khusinov.karvon.domain.use_case.shops

import kotlinx.coroutines.flow.Flow
 import uz.khusinov.karvon.domain.model.shop.Shop
 import uz.khusinov.karvon.domain.repository.ShopsRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateList

class ShopsUseCase(
    private val repository: ShopsRepository
) {
    operator fun invoke(): Flow<UiStateList<Shop>> {
        return repository.getShops()
    }
}