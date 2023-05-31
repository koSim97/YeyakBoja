package com.kosim97.yeyakboja.ui.home.camping

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.yeyakboja.R
import com.kosim97.yeyakboja.databinding.CampingRvItemBinding
import com.kosim97.yeyakboja.ui.home.HomeFragmentDirections

class CampingAdapter(navController: NavController): ListAdapter<CampingDomainModel, CampingAdapter.CampingAdapterViewHolder>(diffUtil) {

    private var mContext: Context? = null
    private val mNavController = navController
    inner class CampingAdapterViewHolder(private val binding: CampingRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: CampingDomainModel) {
            binding.campingItem = currentItem
            mContext?.let {
                Glide.with(it).load(currentItem.campingImage).into(binding.campingIv)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampingAdapterViewHolder {
        val binding = CampingRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return CampingAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CampingAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToDetailFragment(campingItem = getItem(position), gymItem = null, false)
            mNavController.navigate(action)
        }
    }

    override fun submitList(list: MutableList<CampingDomainModel>?) {
        super.submitList(list)
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<CampingDomainModel>(){
            override fun areItemsTheSame(
                oldItem: CampingDomainModel,
                newItem: CampingDomainModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CampingDomainModel,
                newItem: CampingDomainModel
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    }
}