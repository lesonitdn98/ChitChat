package tech.leson.chitchat.ui.search.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.view.*
import tech.leson.chitchat.model.User
import tech.leson.chitchat.ui.base.BaseViewHolder
import tech.leson.chitchat.ui.profile.ProfileActivity

class SearchViewHolder(itemView: View) : BaseViewHolder<User>(itemView) {
    @SuppressLint("SetTextI18n")
    public override fun onBind(data: User) {
        Glide.with(itemView.context).load(data.avatar).centerCrop().into(itemView.imvAvatarList)
        itemView.fullName.text = data.full_name
        itemView.username.text = "@${data.username}"
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, ProfileActivity::class.java)
            intent.putExtra("username", data.username)
            it.context.startActivity(intent)
        }
    }
}
