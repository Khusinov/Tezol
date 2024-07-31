package uz.khusinov.karvon.data.remote

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import uz.khusinov.karvon.domain.model.ConfirmResponse
import uz.khusinov.karvon.domain.model.LoginRequest
import uz.khusinov.karvon.domain.model.LoginResponse
import uz.khusinov.karvon.domain.model.Order
import uz.khusinov.karvon.domain.model.base.BaseResponseList
import uz.khusinov.karvon.domain.model.base.BaseResponseObject


interface ApiService {

    @POST("reg")
    @FormUrlEncoded
    suspend fun login(@Field("phone") phone: String): BaseResponseObject<String>

    @POST("check-sms/{phone}")
    @FormUrlEncoded
    suspend fun confirm(
        @Field("verify_code") verify_code: String,
        @Path("phone") phone: String
    ): BaseResponseObject<ConfirmResponse>


    @GET("get/orders/")
    suspend fun getOrders(): BaseResponseList<Order>



//    @GET("http://router.project-osrm.org/route/v1/driving/{points}?steps=true")
//    suspend fun getRoute(
//        @Path("points") points: String,
//        @Query("bearings") bearings: String,
//        @Query("geometries") geometries: String = "geojson",
//        @Query("overview") overView: String = "full"
//    ): OSMRouteResponse
}
