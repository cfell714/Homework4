package com.example.homework4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DreamListAdapter : ListAdapter<Dream, DreamListAdapter.DreamViewHolder>(DreamComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder {
        return DreamViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DreamViewHolder, position: Int) {
        val currentDream = getItem(position)
        holder.bindText(currentDream.date, holder.idTextView)
        holder.bindText(currentDream.title, holder.titleTextView)

        holder.itemView.setOnClickListener{
            v:View -> Unit

            val context = holder.titleTextView.context

            val intent = Intent(context, ThirdActivity::class.java)
            intent.putExtra("id", currentDream.id.toString())
            context.startActivity(intent)
        }
    }

    class DreamViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val idTextView: TextView = itemView.findViewById(R.id.textView_id)
        val titleTextView: TextView = itemView.findViewById(R.id.textView_title)


        fun bindText (text:String?, textview: TextView){
            textview.text = text
        }

        companion object{
            fun create (parent: ViewGroup) : DreamViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dream, parent, false)
                return DreamViewHolder(view)
            }
        }
    }

    class DreamComparator : DiffUtil.ItemCallback<Dream>(){
        override fun areContentsTheSame(oldItem: Dream, newItem: Dream): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Dream, newItem: Dream): Boolean {
            return oldItem === newItem
        }
    }


}