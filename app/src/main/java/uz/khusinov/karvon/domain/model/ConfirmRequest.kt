package uz.khusinov.karvon.domain.model

data class ConfirmRequest(
    val verify_code: String,
    val phone:String
)