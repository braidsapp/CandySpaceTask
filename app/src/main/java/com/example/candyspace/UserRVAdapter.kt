package com.example.candyspace

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserRVAdapter(
    private val userList: ArrayList<User>,
    private val clickListener: OnItemClickListener,
): RecyclerView.Adapter<UserRVAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val tvUserName : TextView = view.findViewById(R.id.textViewUserName)
        val tvUserId : TextView = view.findViewById(R.id.textViewUserId)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                clickListener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.tvUserId.setText(currentItem.id.toString())
        holder.tvUserName.setText(currentItem.name)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}