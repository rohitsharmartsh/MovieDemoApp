package com.example.myapplication.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.InflatorInstantSearchProductBinding
import com.example.myapplication.model.Search

class InstantSearchAdapter(private var list: List<Search>, var listener: InstantSearchListener) :
    RecyclerView.Adapter<InstantSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<InflatorInstantSearchProductBinding>(
            inflater,
            R.layout.inflator_instant_search_product, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position]!!)
    }

    inner class ViewHolder(val binding: InflatorInstantSearchProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Search) {
            binding.model = item
            binding.listener = listener
        }
    }

    interface InstantSearchListener {
        fun movieDetail(item: Search)
    }


}