package haedab.haedab.haedab.presentation.main.chatting

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import haedab.haedab.haedab.R
import haedab.haedab.haedab.database.RoomEntity
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class ChatRVAdapter @Inject constructor():RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var messageList: MutableList<RoomEntity> = mutableListOf()
    var onCopyClick: ((RoomEntity) -> Unit)? = null
    var onUnlockClick: ((RoomEntity) -> Unit)? = null

    class UserMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userMessageTV: TextView = itemView.findViewById(R.id.userMessageTV)
        val time : TextView = itemView.findViewById(R.id.time)
    }

    class BotMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val botMessageTV: TextView = itemView.findViewById(R.id.bot_message_tv)
        val time : TextView = itemView.findViewById(R.id.time)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_USER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.user_message_rv_item, parent, false)
                UserMessageViewHolder(view)
            }
            VIEW_TYPE_BOT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.bot_message_fntz, parent, false)
                BotMessageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid ui type")
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val text = messageList[position].message

        holder.itemView.setOnClickListener{
            itemClickListener.onLongClick(it, position, text)
        }

        when (holder.itemViewType) {
            VIEW_TYPE_USER -> {(holder as UserMessageViewHolder).userMessageTV.text = messageList[position].message
                /*val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("h:mm a")
                val formatted = current.format(formatter)*/
                val currentTime = Calendar.getInstance().time
                val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
                val formatted = format.format(currentTime)
                (holder as UserMessageViewHolder).time.text = formatted


            }
            VIEW_TYPE_BOT -> {

                val botMessage = messageList[position]
                val botViewHolder = holder as BotMessageViewHolder
                botViewHolder.botMessageTV.text = botMessage.message

                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("h:mm a")
                val formatted = current.format(formatter)
                botViewHolder.time.text = formatted
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (messageList[position].sender) {
            "user" -> VIEW_TYPE_USER
            "bot" -> VIEW_TYPE_BOT
            else -> throw IllegalArgumentException("Invalid message type")
        }
    }

    companion object {
        private const val VIEW_TYPE_USER = 0
        private const val VIEW_TYPE_BOT = 1
    }


    interface OnItemClickListener {
        fun onLongClick(v: View, position: Int, text: String)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener
}