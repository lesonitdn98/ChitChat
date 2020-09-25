package tech.leson.chitchat.ui.update.dialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import tech.leson.chitchat.R
import tech.leson.chitchat.ui.update.dialog.model.Avatar

class AvatarAdapter(context: Context, data: List<Avatar>, onClickItemAvatar: OnClickItemAvatar) :
    RecyclerView.Adapter<AvatarAdapter.ViewHolder>() {

    private val mContext = context
    private val mData = data
    private val mOnClickItemAvatar = onClickItemAvatar

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: CircleImageView = itemView.findViewById(R.id.itemAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rcv_avatar, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(mContext).load(mData[position].resId).centerCrop().into(holder.item)
        holder.item.setOnClickListener { mOnClickItemAvatar.onItemClick(mData[position]) }
    }

    override fun getItemCount(): Int = mData.size
}
