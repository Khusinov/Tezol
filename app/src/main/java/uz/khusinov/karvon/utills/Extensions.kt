@file:Suppress("DEPRECATION")

package uz.khusinov.marjonamarketcourier2.utills

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat

fun Throwable.userMessage() = when (this) {
    is HttpException -> when (this.code()) {
        304 -> "304 Not Modified"
        400 -> "400 Bad Request"
        401 -> "401 Unauthorized"
        403 -> "403 Forbidden"
        404 -> "404 Not Found"
        405 -> "405 Method Not Allowed"
        409 -> "User не найден"
        422 -> "422 Unprocessable"
        500 -> "500 Server Error"
        else -> "Something went wrong"
    }

    is IOException -> "Internet bilan aloqa yo'q"
    is JsonSyntaxException -> "Ma'lumot olishda xatolik"
    else -> this.localizedMessage
}!!

@SuppressLint("SimpleDateFormat")
fun stringToSeconds(dateTimeString: String): Long {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    try {
        val date = sdf.parse(dateTimeString)
        val currentTime = System.currentTimeMillis()
        if (date != null) {
            val a = (date.time - currentTime)
            return if (a > 0) a
            else 0
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return 0
}

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun <T> MutableLiveData<MutableList<T>>.addNewItem(item: T) {
    val oldValue = this.value ?: mutableListOf()
    oldValue.add(item)
    this.postValue(oldValue)
}

suspend fun <T> MutableStateFlow<T?>.set(value: T, idle: T? = null, delay: Long = 100) {
    this.value = value
    delay(delay)
    this.value = idle
}

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit,
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}


fun Context.isMyServiceRunning(serviceClass: Class<*>): Boolean {
    val manager = getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager
    for (service in manager.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}

fun NavController.navigateSafe(@IdRes resId: Int, args: Bundle? = null) {
    val destinationId = currentDestination?.getAction(resId)?.destinationId.orEmpty()
    currentDestination?.let { node ->
        val currentNode = when (node) {
            is NavGraph -> node
            else -> node.parent
        }
        if (destinationId != 0) {
            currentNode?.findNode(destinationId)?.let { navigate(resId, args) }
        }
    }
}

fun Int?.orEmpty(default: Int = 0): Int {
    return this ?: default
}

fun Context.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun <T, K, R> LiveData<T>.combineWith(
    liveData: LiveData<K>,
    block: (T?, K?) -> R
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        result.value = block(this.value, liveData.value)
    }
    result.addSource(liveData) {
        result.value = block(this.value, liveData.value)
    }
    return result
}

//fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
//    convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))
//
//private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
//    if (sourceDrawable == null) {
//        return null
//    }
//    return if (sourceDrawable is BitmapDrawable) {
//        sourceDrawable.bitmap
//    } else {
//        val constantState = sourceDrawable.constantState ?: return null
//        val drawable = constantState.newDrawable().mutate()
//        val bitmap: Bitmap = Bitmap.createBitmap(
//            drawable.intrinsicWidth, drawable.intrinsicHeight,
//            Bitmap.Config.ARGB_8888
//        )
//        val canvas = Canvas(bitmap)
//        drawable.setBounds(0, 0, canvas.width, canvas.height)
//        drawable.draw(canvas)
//        bitmap
//    }
//}

fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int): Bitmap? {
    return convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))
}

private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
    if (sourceDrawable == null) {
        return null
    }
    return if (sourceDrawable is BitmapDrawable) {
        Bitmap.createScaledBitmap(sourceDrawable.bitmap, 150, 150, false)
    } else {
        val constantState = sourceDrawable.constantState ?: return null

        val drawable = constantState.newDrawable().mutate()
        val bitmap: Bitmap = Bitmap.createBitmap(150, 150, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        bitmap
    }
}

@SuppressLint("NewApi")
fun price(price: Int): String {
    val decimalFormatSymbols = DecimalFormatSymbols()
    decimalFormatSymbols.groupingSeparator = ' '
    val decimalFormat = DecimalFormat("###,###", decimalFormatSymbols)
    return decimalFormat.format(price)
}

