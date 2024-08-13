package uz.khusinov.karvon.presentation.shops.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.khusinov.karvon.databinding.ShopItemBinding
import uz.khusinov.karvon.domain.model.shop.Shop
import uz.khusinov.karvon.utills.Constants

class ShopsAdapter(private val onItemClicked: (shop: Shop) -> Unit) :
    RecyclerView.Adapter<ShopsAdapter.ViewHolder>() {
    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = dif.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.setContent(position)

    inner class ViewHolder(private val binding: ShopItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
        fun setContent(position: Int) = with(binding) {
            val shop = dif.currentList[position]
            if (shop.img != null) {
                Log.d("TAG", "setContent: image is not null ")
                Picasso.get().load("${Constants.IMAGE_URL}${shop.img}")
                    .into(binding.shopImage)
            } else Log.d("TAG", "setContent: image is null ")


            shopItem.setOnClickListener {
                onItemClicked(shop)
            }
        }
    }

    fun submitList(shop: List<Shop>) {
        dif.submitList(shop)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Shop>() {
            override fun areItemsTheSame(oldItem: Shop, newItem: Shop): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Shop, newItem: Shop): Boolean =
                oldItem == newItem
        }
    }
}