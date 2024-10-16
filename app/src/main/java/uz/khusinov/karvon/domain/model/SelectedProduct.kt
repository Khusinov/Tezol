package uz.khusinov.karvon.domain.model

import uz.khusinov.karvon.domain.model.shop.Product


data class SelectedProduct(val product: Product, val options: List<Options>, var count: Int)
