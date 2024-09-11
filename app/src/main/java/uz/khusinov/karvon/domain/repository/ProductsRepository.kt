package uz.khusinov.karvon.domain.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.marjonamarketcourier2.utills.UiStateList

interface ProductsRepository {
    suspend fun insertProduct(products: Product)

    fun getProductsOnBasket(): Flow<UiStateList<Product>>

    suspend fun removeProduct(products: Product)

    suspend fun updateProduct(products: Product)

    suspend fun deleteAllProducts() {
        Log.d("TAG", "deleteAllProducts: called ")
    }
}