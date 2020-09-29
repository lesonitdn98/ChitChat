package tech.leson.chitchat.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.leson.chitchat.R
import tech.leson.chitchat.model.User
import tech.leson.chitchat.ui.base.BaseAdapter
import tech.leson.chitchat.ui.base.BaseViewHolder
import javax.inject.Inject

class SearchAdapter @Inject constructor(data: MutableList<User>) :
    BaseAdapter<BaseViewHolder<*>, User>(data) {

    override fun addData(data: MutableList<User>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user,
            parent,
            false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is SearchViewHolder) {
            holder.onBind(data[position])
        }
    }

    override fun getItemCount(): Int = data.size
}
