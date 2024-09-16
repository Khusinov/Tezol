package uz.khusinov.karvon.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.khusinov.karvon.data.local.ProductsDao
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.repository.ProductsRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateList

class ProductsRepositoryImpl(private val productsDao: ProductsDao) : ProductsRepository {
    override suspend fun insertProduct(products: Product) {
        productsDao.insertProduct(products)
    }

    override fun getProductsOnBasket(): Flow<UiStateList<Product>> = flow {
        emit(UiStateList.LOADING)
        try {
            val response = productsDao.getProductsOnBasket()
            emit(UiStateList.SUCCESS(response))
        } catch (e: Exception) {
        }
    }

    override suspend fun removeProduct(products: Product) {
        productsDao.removeProduct(products)
    }

    override suspend fun updateProduct(products: Product) {
        productsDao.updateProduct(products)
    }

    override suspend fun deleteAllProducts() {
        productsDao.deleteAllProducts()
    }

}