package com.example.phonedial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendsAdapter(private val contacts: MutableList<Friend>? = arrayListOf()): RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_row, parent, false)
        return FriendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        if (this.contacts.isNullOrEmpty()) return 0
        else return this.contacts.size
    }

    var onItemClick: ((Int) -> Unit)? = null

    inner class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.nameTV)
        private val phone = itemView.findViewById<TextView>(R.id.phoneTV)

        fun bind(pos: Int) {
            name.setText(contacts?.get(pos)?.name)
            phone.setText(contacts?.get(pos)?.phone)
        }
    }
}