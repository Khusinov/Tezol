package uz.khusinov.karvon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.marjonamarket.data.local.Convertors


@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getProductsDao(): ProductsDao
}
