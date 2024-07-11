package uz.khusinov.karvon.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.khusinov.karvon.domain.model.LoginRequest
import uz.khusinov.karvon.domain.model.LoginResponse
import uz.khusinov.karvon.domain.model.Order
import uz.khusinov.karvon.domain.model.base.BaseResponseList
import uz.khusinov.karvon.domain.model.base.BaseResponseObject


interface ApiService {

    @POST("auth/login/courier")
    suspend fun login(@Body loginRequest: LoginRequest): BaseResponseObject<LoginResponse>


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
