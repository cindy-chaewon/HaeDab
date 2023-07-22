package haedab.haedab.haedab.presentation.main.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import haedab.haedab.haedab.R
import haedab.haedab.haedab.databinding.ItemLangaugeBinding


class LanguageAdapter(private val dataList : ArrayList<Language>): RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    var selectPos = -1

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

        if(selectPos == position){
            holder.itemView.setBackgroundResource(R.drawable.language_background_change)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.language_background)
        }
        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)

            val beforePos = selectPos
            selectPos = position

            notifyItemChanged(beforePos)
            notifyItemChanged(selectPos)
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