package haedab.haedab.haedab.presentation.main.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import haedab.haedab.haedab.databinding.ItemCategoryListBinding


class CategorySecondAdapter(private val dataList : ArrayList<Category>) : RecyclerView.Adapter<CategorySecondAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemCategoryListBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(data: Category){
            binding.itemCategoryTv.text = data.title
            Glide.with(binding.itemCategoryImg.context)
                .load(data.img)
                .into(binding.itemCategoryImg)
        }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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