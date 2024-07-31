package uz.khusinov.karvon.presentation.shops.selectedShop

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.khusinov.karvon.databinding.ProductItemBinding
 import uz.khusinov.karvon.domain.model.shop.Product

class SelectedProductsAdapter(private val onItemClicked: (product: Product) -> Unit) :
    RecyclerView.Adapter<SelectedProductsAdapter.ViewHolder>() {
    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = dif.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.setContent(position)

    inner class ViewHolder(private val binding: ProductItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
        fun setContent(position: Int) = with(binding) {
            val shop = dif.currentList[position]
//            if (shop.img != null){
//                Log.d("TAG", "setContent: image is not null ")
//                Picasso.get().load("${Constants.IMAGE_URL}${shop.img}")
//                    .into(binding.catalogImage)
//            } else  Log.d("TAG", "setContent: image is null ")


            item.setOnClickListener {
                onItemClicked(shop)
            }
        }
    }

    fun submitList(shop: List<Product>) {
        dif.submitList(shop)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem == newItem
        }
    }
}