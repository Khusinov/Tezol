package uz.khusinov.karvon.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import uz.khusinov.karvon.domain.model.AdsResponse
import uz.khusinov.karvon.domain.model.ConfirmRequest
import uz.khusinov.karvon.domain.model.ConfirmResponse
import uz.khusinov.karvon.domain.model.LoginRequest
import uz.khusinov.karvon.domain.model.Order
import uz.khusinov.karvon.domain.model.base.BaseResponseList
import uz.khusinov.karvon.domain.model.base.BaseResponseObject
import uz.khusinov.karvon.domain.model.shop.CategoryRespons
import uz.khusinov.karvon.domain.model.shop.Categorys
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.model.shop.Shop
import uz.khusinov.karvon.domain.model.shop.Shops


interface ApiService {

    @POST("auth/send-sms/")
    suspend fun login(@Body phone: LoginRequest): BaseResponseObject<Unit>

    @POST("auth/verify-sms/")
    suspend fun confirm(
        @Body confirmRequest: ConfirmRequest
    ): BaseResponseObject<ConfirmResponse>


    @GET("shop/")
    suspend fun getShops(@Query("page") page: Int, @Query("page_size") pageSize: Int = 20): BaseResponseObject<Shops>

    @GET("shop/product/{shopId}")
    suspend fun getShopsProducts(@Path("shopId") shopId: Int): BaseResponseList<Product>

    @GET("shop")
    suspend fun getOrders(): BaseResponseList<Order>

    @GET("category")
    suspend fun getCategories(@Query("page") page: Int, @Query("page_size") pageSize: Int = 20): BaseResponseObject<Categorys>

    @GET("category/{id}")
    suspend fun getCategory(
        @Path("id") id: Int
    ): BaseResponseObject<CategoryRespons>

    @GET("ads")
    suspend fun getAds(): BaseResponseObject<AdsResponse>

//    @GET("http://router.project-osrm.org/route/v1/driving/{points}?steps=true")
//    suspend fun getRoute(
//        @Path("points") points: String,
//        @Query("bearings") bearings: String,
//        @Query("geometries") geometries: String = "geojson",
//        @Query("overview") overView: String = "full"
//    ): OSMRouteResponse
}
