package com.aptyr.clonegithubtofirebase.ui.adapter

/**
 * Copyright (C) 2016 Aptyr (github.com/aptyr)

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aptyr.clonegithubtofirebase.R

import java.util.ArrayList

import com.aptyr.clonegithubtofirebase.model.User
import com.aptyr.clonegithubtofirebase.ui.widget.UsersRecyclerViewRow


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    private val mUsers = ArrayList<User>()
    private var mViewHolder: ViewHolder? = null
    private var mItemClickListener: ItemClickListener? = null

    private var mExpandRowPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        mViewHolder = ViewHolder(view)
        mViewHolder!!.mItemClickListener = mItemClickListener
        return mViewHolder!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mUsers[position]
        holder.rowView?.setData(user)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    fun addUsers(users: List<User>) {
        Log.d("addusers", "$users")
        mUsers.addAll(users)
        notifyItemRangeInserted(mUsers.size - users.size, users.size)
    }

    fun setItemClickListener(clickListener: ItemClickListener) {
        mItemClickListener = clickListener
    }

    fun expandRow(position: Int) {
        if (position == mExpandRowPosition)
            return

        val old = mExpandRowPosition
        mExpandRowPosition = position

        if (old != -1)
            notifyItemChanged(old)

        notifyItemChanged(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rowView: UsersRecyclerViewRow? = null
        var mItemClickListener: ItemClickListener? = null

        init {

            rowView = itemView.findViewById(R.id.row) as UsersRecyclerViewRow
            rowView?.setOnClickListener({ view -> mItemClickListener?.onItemClick(adapterPosition) })
        }

    }
}
