package com.example.haedab.presentation.main.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.haedab.databinding.FragmentLanguageBinding
import com.example.haedab.databinding.ItemCategoryListBinding
import com.example.haedab.databinding.ItemLangaugeBinding

class LanguageAdapter(private val dataList : ArrayList<Language>): RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemLangaugeBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(data: Language){
                    binding.itemLanguageTv.text = data.title
                    Glide.with(binding.itemLanguageImg.context)
                        .load(data.img)
                        .into(binding.itemLanguageImg)
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLangaugeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener
}