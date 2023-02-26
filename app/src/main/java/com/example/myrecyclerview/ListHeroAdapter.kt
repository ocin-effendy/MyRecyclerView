package com.example.myrecyclerview

import Hero
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.myrecyclerview.databinding.ItemRowHeroBinding

class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    // with binding
//    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
//        val binding = ItemRowHeroBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
//        return ListViewHolder(binding)
//    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listHero[position]
        Log.i("PHOTO", photo)
//        holder.imgPhoto.setImageResource(photo) // using drawable
        Glide.with(holder.itemView.context)
            .load("https://upload.wikimedia.org/wikipedia/commons/3/3f/Ahmad_Yani.jpg")
            .placeholder(R.drawable.bung_tomo)
            .into(holder.imgPhoto) // using glide
        holder.tvName.text = name
        holder.tvDescription.text = description

        // with binding
//        Glide.with(holder.binding.root)
//            .load(photo)
//            .placeholder(R.drawable.bung_tomo)
//            .dontAnimate()
//            .into(holder.binding.imgItemPhoto) // using glide
//
//        holder.binding.tvItemName.text = name
//        holder.binding.tvItemDescription.text = description


//        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "Kamu memilih " + listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
//        }

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listHero.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    // with binding
//    class ListViewHolder(var binding: ItemRowHeroBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }
}