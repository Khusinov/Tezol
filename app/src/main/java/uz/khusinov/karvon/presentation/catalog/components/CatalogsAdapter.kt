package uz.khusinov.karvon.presentation.catalog.components

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.khusinov.karvon.databinding.CategoryItemBinding
import uz.khusinov.karvon.domain.model.shop.CategoryRespons

class CatalogsAdapter(
    val onItemClick: (bread: Int) -> Unit,
) : PagingDataAdapter<CategoryRespons, CatalogsAdapter.ViewHolder>(ITEM_DIFF) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(getItem(position)!!)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setContent(category: CategoryRespons) = with(binding) {
            categoryItem.setOnClickListener { onItemClick(category.id) }

            binding.apply {
                categoryImage.setOnClickListener { onItemClick(category.id) }
                categoryName.text = category.name
                Picasso.get().load(category.image).into(categoryImage)
            }
        }
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<CategoryRespons>() {
            override fun areItemsTheSame(
                oldItem: CategoryRespons,
                newItem: CategoryRespons
            ): Boolean =
                oldItem.name == newItem.name

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: CategoryRespons,
                newItem: CategoryRespons
            ): Boolean =
                oldItem == newItem
        }
    }
}