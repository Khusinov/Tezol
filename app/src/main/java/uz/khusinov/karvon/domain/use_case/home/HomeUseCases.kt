package uz.khusinov.karvon.domain.use_case.home

data class HomeUseCases(
    val getAdsUseCase: GetAdsUseCase,
    val getNewProductsUseCase: GetNewProductsUseCase,
    val getMostSellProductsUseCase: GetMostSellProductsUseCase,
    val getTopProductsUseCase: GetTopProductsUseCase
)
