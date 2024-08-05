package uz.khusinov.karvon.domain.model.shop

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shop(
    val address_name: String,
    val balans: String?,
    val close_time: String,
    val created_at: String,
    val deleted_at: String?,
    val id: Int,
    val img: String?,
    val info: String?,
    val inn: String,
    val lang: String?,
    val lat: String?,
    val name: String,
    val open_time: String,
    val phone: String,
    val status: String?,
    val updated_at: String
) : Parcelable