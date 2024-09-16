package uz.khusinov.karvon.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.khusinov.karvon.domain.model.shop.Product

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: Product)

    @Query("SELECT * FROM product")
    suspend fun getProductsOnBasket(): List<Product>

    @Delete
    suspend fun removeProduct(products: Product)

    @Update
    suspend fun updateProduct(products: Product)

    @Query("DELETE FROM product")
    suspend fun deleteAllProducts()
}