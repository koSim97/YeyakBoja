package com.kosim97.yeyakboja.ui.home.football

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.yeyakboja.databinding.FootballRvItemBinding
import com.kosim97.yeyakboja.ui.home.HomeFragmentDirections

class FootballAdapter(navController: NavController): ListAdapter<GymDomainModel, FootballAdapter.FootballAdapterViewHolder>(diffUtil) {
    private var mContext: Context? = null
    private val mNavController = navController

    inner class FootballAdapterViewHolder(private val binding: FootballRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: GymDomainModel) {
            binding.gymItem = currentItem
            mContext?.let {
                Glide.with(it).load(currentItem.gymImage).into(binding.gymIv)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballAdapterViewHolder {
        val binding = FootballRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return FootballAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FootballAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToDetailFragment(campingItem = null, gymItem = getItem(position), true)
            mNavController.navigate(action)
        }
    }

    override fun submitList(list: MutableList<GymDomainModel>?) {
        super.submitList(list)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GymDomainModel>(){
            override fun areItemsTheSame(
                oldItem: GymDomainModel,
                newItem: GymDomainModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: GymDomainModel,
                newItem: GymDomainModel
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    }


}