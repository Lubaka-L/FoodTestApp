package com.example.foodtestapp.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtestapp.R
import com.example.foodtestapp.databinding.CategoryItemBinding

interface DishCategoryAdapterDelegate {
    fun onDishCategoryClicked(dishCategory: DishCategoryAdapter.SelectedDishCategoryModelRv)
}

class DishCategoryAdapter(private var delegate: DishCategoryAdapterDelegate) :
    ListAdapter<DishCategoryAdapter.SelectedDishCategoryModelRv, DishCategoryAdapter.ViewHolder>(
        diffUtil
    ) {

    data class SelectedDishCategoryModelRv(
        val text: String,
        val isSelected: Boolean
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), delegate
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    class ViewHolder(
        private val binding: CategoryItemBinding,
        private var delegate: DishCategoryAdapterDelegate
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(dishCategory: SelectedDishCategoryModelRv) {
            binding.apply {
                if (dishCategory.isSelected) {
                    title.text = dishCategory.text
                    root.setCardBackgroundColor(root.context.resources.getColor(R.color.pink, null))
                } else {
                    title.text = dishCategory.text
                    root.setCardBackgroundColor(root.context.resources.getColor(R.color.white, null))
                }
                root.setOnClickListener {
                    delegate.onDishCategoryClicked(dishCategory)
                }
            }
        }
    }


    companion object {
        var diffUtil = object : DiffUtil.ItemCallback<SelectedDishCategoryModelRv>() {

            override fun areItemsTheSame(
                oldItem: SelectedDishCategoryModelRv,
                newItem: SelectedDishCategoryModelRv
            ): Boolean {
                return oldItem.text == newItem.text
                        && oldItem.isSelected == newItem.isSelected
            }

            override fun areContentsTheSame(
                oldItem: SelectedDishCategoryModelRv,
                newItem: SelectedDishCategoryModelRv
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


}