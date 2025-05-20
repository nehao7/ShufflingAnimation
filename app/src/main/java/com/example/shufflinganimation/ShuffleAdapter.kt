package com.example.shufflinganimation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shufflinganimation.databinding.RecyclerItemBinding
import java.util.Collections

class ShuffleAdapter(var list : ArrayList<String>): RecyclerView.Adapter<ShuffleAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var binding= RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    class ViewHolder(var binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onBindViewHolder(holder: ShuffleAdapter.ViewHolder, position: Int) {
        holder.apply {
            binding.text.setText(list[position])
        }
    }


    override fun getItemCount(): Int {
       return list.size
    }

   }