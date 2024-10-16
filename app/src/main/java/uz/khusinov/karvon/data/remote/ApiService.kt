package uz.khusinov.karvon.data.remote

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import uz.khusinov.karvon.domain.model.ConfirmRequest
import uz.khusinov.karvon.domain.model.ConfirmResponse
import uz.khusinov.karvon.domain.model.LoginRequest
import uz.khusinov.karvon.domain.model.LoginResponse
import uz.khusinov.karvon.domain.model.Order
import uz.khusinov.karvon.domain.model.base.BaseResponseList
import uz.khusinov.karvon.domain.model.base.BaseResponseObject
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.domain.model.shop.Shop


interface ApiService {

    @POST("auth/send-sms/")
    suspend fun login(@Body phone: LoginRequest): BaseResponseObject<Unit>

    @POST("auth/verify-sms/")
    suspend fun confirm(
        @Body confirmRequest: ConfirmRequest
    ): BaseResponseObject<ConfirmResponse>

    @GET("shop")
    suspend fun getShops(): BaseResponseList<Shop>

    @GET("shop/product/{shopId}")
    suspend fun getShopsProducts(@Path("shopId") shopId: Int): BaseResponseList<Product>

    @GET("shop")
    suspend fun getOrders(): BaseResponseList<Order>


//    @GET("http://router.project-osrm.org/route/v1/driving/{points}?steps=true")
//    suspend fun getRoute(
//        @Path("points") points: String,
//        @Query("bearings") bearings: String,
//        @Query("geometries") geometries: String = "geojson",
//        @Query("overview") overView: String = "full"
//    ): OSMRouteResponse
}
