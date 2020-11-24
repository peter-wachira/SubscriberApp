package com.anushka.roomdemo.ui

import android.database.DatabaseUtils
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.roomdemo.R
import com.anushka.roomdemo.databinding.ListItemBinding
import com.anushka.roomdemo.db.Subscriber
import com.anushka.roomdemo.generated.callback.OnClickListener
import kotlinx.android.synthetic.main.list_item.view.*


class MyRecyclerViewAdapter (private  val subscriberList: List<Subscriber>,private  val clickListener: (Subscriber)->Unit) :RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding :ListItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position],clickListener)
    }

    override fun getItemCount(): Int {
    return  subscriberList.size
    }

}


class MyViewHolder(val binding: ListItemBinding) :RecyclerView.ViewHolder(binding.root){
    fun  bind(subscriber: Subscriber,clickListener: (Subscriber) -> Unit){
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItenLayout.setOnClickListener{clickListener(subscriber)}

    }
}
