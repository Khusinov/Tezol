package uz.khusinov.karvon.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.khusinov.karvon.data.remote.ApiService
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.model.shop.Shop
import uz.khusinov.karvon.domain.repository.ShopsRepository
import uz.khusinov.marjonamarketcourier2.utills.UiStateList
import uz.khusinov.marjonamarketcourier2.utills.userMessage

class ShopsRepositoryImpl(
    private val apiService: ApiService
) : ShopsRepository {

    override fun getShops(): Flow<UiStateList<Shop>> = flow {
        emit(UiStateList.LOADING)
        try {
            val response = apiService.getShops()
            if (response.success) {
                emit(UiStateList.SUCCESS(response.data))
            } else {
                emit(UiStateList.ERROR(response.error.message))
            }
        } catch (e: Exception) {
            emit(UiStateList.ERROR(e.userMessage()))
        }
    }

    override fun getShopsProducts(shopId: Int): Flow<UiStateList<Product>> = flow {
        emit(UiStateList.LOADING)
        try {
            val response = apiService.getShopsProducts(shopId)
            if (response.success) {
                Log.d("TAG", "getShopsProducts: success ")
                emit(UiStateList.SUCCESS(response.data))
            } else {
                Log.d("TAG", "getShopsProducts: error ")
                emit(UiStateList.ERROR(response.error.message))
            }
        } catch (e: Exception) {
            Log.d("TAG", "getShopsProducts: catch ${e.message} ")
            emit(UiStateList.ERROR(e.userMessage()))
        }
    }

//    override fun getOrderDetail(id: Int): Flow<UiStateObject<Order>> = flow {
//        emit(UiStateObject.LOADING)
//        try {
//            val response = apiService.getOrderDetail(id)
//            if (response.success) {
//                emit(UiStateObject.SUCCESS(response.result))
//            } else {
//                emit(UiStateObject.ERROR(response.error.message))
//            }
//        } catch (e: Exception) {
//            emit(UiStateObject.ERROR(e.localizedMessage ?: e.userMessage()))
//        }
//    }

//    override fun acceptOrder(orderId: Int): Flow<UiStateObject<Order>> = flow {
//        emit(UiStateObject.LOADING)
//        try {
//            val response = apiService.acceptOrder(orderId)
//            if (response.success) {
//                emit(UiStateObject.SUCCESS(response.result))
//            } else {
//                emit(UiStateObject.ERROR(response.error.message))
//            }
//        } catch (e: Exception) {
//            emit(UiStateObject.ERROR(e.localizedMessage ?: e.userMessage()))
//        }
//    }

//    override fun arrive(orderId: Int): Flow<UiStateObject<Order>> = flow {
//        emit(UiStateObject.LOADING)
//        try {
//            val response = apiService.arrive(orderId)
//            if (response.success) {
//                emit(UiStateObject.SUCCESS(response.result))
//            } else {
//                emit(UiStateObject.ERROR(response.error.message))
//            }
//        } catch (e: Exception) {
//            emit(UiStateObject.ERROR(e.localizedMessage ?: e.userMessage()))
//        }
//    }

//    override fun startOrder(orderId: Int): Flow<UiStateObject<Order>> = flow {
//        emit(UiStateObject.LOADING)
//        try {
//            val response = apiService.startOrder(orderId)
//            if (response.success) {
//                emit(UiStateObject.SUCCESS(response.result))
//            } else {
//                emit(UiStateObject.ERROR(response.error.message))
//            }
//        } catch (e: Exception) {
//            emit(UiStateObject.ERROR(e.localizedMessage ?: e.userMessage()))
//        }
//    }

//    override fun completeOrder(orderId: Int): Flow<UiStateObject<Order>> = flow {
//        emit(UiStateObject.LOADING)
//        try {
//            val response = apiService.completeOrder(orderId)
//            if (response.success) {
//                emit(UiStateObject.SUCCESS(response.result))
//            } else {
//                emit(UiStateObject.ERROR(response.error.message))
//            }
//        } catch (e: Exception) {
//            emit(UiStateObject.ERROR(e.localizedMessage ?: e.userMessage()))
//        }
//    }

//    override fun getMyOrdersHistory(): Flow<UiStateObject<Data<OrderHistory>>> = flow {
//        emit(UiStateObject.LOADING)
//        try {
//            val response = apiService.getMyOrdersHistory(1)
//            if (response.success) {
//                emit(UiStateObject.SUCCESS(response.result))
//            } else {
//                emit(UiStateObject.ERROR(response.error.message))
//            }
//        } catch (e: Exception) {
//            emit(UiStateObject.ERROR(e.localizedMessage ?: e.userMessage()))
//        }
//    }
}