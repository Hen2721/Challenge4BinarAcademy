package com.example.challenge4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.example.challenge4.core.domain.model.Book
import com.example.challenge4.databinding.BookItemBinding
import java.io.File

class BookAdapter(private val bookList: List<Book>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
        fun onEditClicked(data: Book)
        fun onDeleteClicked(data: Book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookList[position]
        holder.bind(book)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(book)
        }
        holder.binding.btnEditName.setOnClickListener {
            onItemClickCallback.onEditClicked(book)
        }
        holder.binding.btnDeleteName.setOnClickListener {
            onItemClickCallback.onDeleteClicked(book)
        }
    }

    class ViewHolder(val binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = bookList.size

    companion object {

        @JvmStatic
        @BindingAdapter("setPhoto")
        fun setPhoto(imgPhoto: ShapeableImageView, img: File) {
            Glide.with(imgPhoto)
                .load(img)
                .into(imgPhoto)
        }
    }

}