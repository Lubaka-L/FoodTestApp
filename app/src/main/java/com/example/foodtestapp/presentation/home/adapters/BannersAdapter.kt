package com.example.foodtestapp.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.Banner
import com.example.foodtestapp.databinding.BannerItemBinding


class BannersAdapter() :
    ListAdapter<Banner, BannersAdapter.ViewHolder>(
        diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BannerItemBinding.inflate(
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
        private val binding: BannerItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(banner: Banner) {
            binding.apply {
                Glide.with(root.context).load(banner.banner).into(image)
            }
        }
    }


    companion object {
        var diffUtil = object : DiffUtil.ItemCallback<Banner>() {

            override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
                return oldItem.banner == newItem.banner
            }

            override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
                return oldItem == newItem
            }
        }
    }


}