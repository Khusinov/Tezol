package uz.khusinov.karvon.presentation.home.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import uz.khusinov.karvon.R

class AdsAdapter(
    private val context: Context,
    private val dataList: MutableList<String>
) : PagerAdapter() {

    override fun getCount(): Int = dataList.size

    override fun isViewFromObject(view: View, image: Any): Boolean = (view == image)

    @SuppressLint("MissingInflatedId")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ads, container, false)
        Log.d("TAG", "instantiateItem: ${dataList[position]}")
        Picasso.get().load(dataList[position]).into(view.findViewById<ImageView>(R.id.ads_image))
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) =
        container.removeView(view as View?)
}