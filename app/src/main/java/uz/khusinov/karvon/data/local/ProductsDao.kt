package uz.khusinov.marjonamarket.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.khusinov.marjonamarket.data.model.Products

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: Products)

    @Query("SELECT * FROM Products")
    suspend fun getProductsOnBasket(): List<Products>

    @Delete
    suspend fun removeProduct(products: Products)

    @Update
    suspend fun updateProduct(products: Products)

    @Query("DELETE FROM Products")
    suspend fun deleteAllProducts()
}