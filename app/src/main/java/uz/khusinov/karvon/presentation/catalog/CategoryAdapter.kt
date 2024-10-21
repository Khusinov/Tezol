package uz.khusinov.karvon.presentation.catalog

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.khusinov.karvon.databinding.CategoryItemBinding
import uz.khusinov.karvon.domain.model.shop.CategoryRespons

@Suppress("UNCHECKED_CAST")
class CategoryAdapter(
    val onItemClick: (position: Int) -> Unit
) : ListAdapter<CategoryRespons, CategoryAdapter.ViewHolder>(ITEM_DIFF) {

    var list: List<CategoryRespons> = ArrayList()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.setContent(getItem(position))

    inner class ViewHolder(private val binding:CategoryItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun setContent(category: CategoryRespons){
            binding.apply {
                categoryImage.setOnClickListener { onItemClick(category.id) }
                categoryName.text = category.name
                Picasso.get().load(category.image).into(categoryImage)
            }
        }
    }

    fun sendData(categories: List<CategoryRespons>) {
        this.list = categories
        submitList(categories)
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<CategoryRespons>() {
            override fun areItemsTheSame(oldItem: CategoryRespons, newItem: CategoryRespons): Boolean =
                oldItem.name == newItem.name

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: CategoryRespons, newItem: CategoryRespons): Boolean =
                oldItem == newItem
        }
    }
}