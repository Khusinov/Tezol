package uz.khusinov.karvon.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.khusinov.karvon.domain.model.Order
import uz.khusinov.marjonamarketcourier2.utills.UiStateList

interface OrdersRepository {
    fun getOrders(): Flow<UiStateList<Order>>

}