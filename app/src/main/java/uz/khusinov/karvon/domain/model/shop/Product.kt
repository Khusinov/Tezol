package uz.khusinov.karvon.domain.model.shop

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Product(
    val category_id: Int,
    val code: String?,
    val count: String,
    val created_at: String,
    val hash_id: Int?,
    val id: Int,
    val img: String?,
    val img2: String?,
    val img3: String?,
    val img4: String?,
    val img5: String?,
    val img6: String?,
    val img7: String?,
    val img8: String?,
    val img9: String?,
    val miqdori: String?,
    val more: String,
    val name: String,
    val new_price: String?,
    val price: String,
    val shop_id: Int,
    val status: Int,
    val type: Int,
    val updated_at: String
) : Parcelable