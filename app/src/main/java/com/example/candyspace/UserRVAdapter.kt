package com.example.candyspace

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.candyspace.R
import com.example.candyspace.RecyclerViewUserInfo

class UserRVAdapter(private val userList: ArrayList<RecyclerViewUserInfo>): RecyclerView.Adapter<UserRVAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvUserName : TextView = view.findViewById(R.id.textViewUserName)
        val tvUserId : TextView = view.findViewById(R.id.textViewUserId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        Log.d("CHK", "Pos$position")
        Log.d("CHK", currentItem.toString())

        holder.tvUserId.setText(currentItem.id.toString())
        holder.tvUserName.setText(currentItem.name)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}