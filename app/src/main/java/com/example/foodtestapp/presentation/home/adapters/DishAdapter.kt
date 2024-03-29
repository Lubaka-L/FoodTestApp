package com.example.foodtestapp.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.Dish
import com.example.foodtestapp.databinding.DishItemBinding

class DishAdapter() :
    ListAdapter<Dish, DishAdapter.ViewHolder>(
        diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DishItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    class ViewHolder(
        private val binding: DishItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(dish: Dish) {
            binding.apply {
                Glide.with(root.context).load(dish.image).into(image)
                title.text = dish.title
                ingredients.text = dish.ingredients.toString().drop(1).dropLast(1)
                price.text = "400p."
            }
        }
    }


    companion object {
        var diffUtil = object : DiffUtil.ItemCallback<Dish>() {

            override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
                return oldItem == newItem
            }
        }
    }


}