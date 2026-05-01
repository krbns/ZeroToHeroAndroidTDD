package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemLayoutBinding


const val ITEMS_KEY = "items"

class ItemsAdapter :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private val items = ArrayList<CharSequence>()

    fun add(text: String) {
        items.add(text)
        notifyItemInserted(items.size - 1)
    }

    fun save(bundle: Bundle) {
        bundle.putCharSequenceArrayList(ITEMS_KEY, items)
    }

    fun restore(bundle: Bundle) {
        items.addAll(bundle.getCharSequenceArrayList(ITEMS_KEY) ?: ArrayList())
        notifyItemRangeInserted(0, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(text: CharSequence) {
            binding.elementTextView.text = text
        }
    }
}