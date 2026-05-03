package ru.easycode.zerotoheroandroidtdd.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemLayoutBinding
import ru.easycode.zerotoheroandroidtdd.main.ItemUi

class ItemsAdapter(
    private val onItemClick: (ItemUi) -> Unit
) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {
    private val items = ArrayList<ItemUi>()

    fun update(list: List<ItemUi>) {
        val diffUtil = DiffUtilCallback(items, list)
        val diff = DiffUtil.calculateDiff(diffUtil)
        items.clear()
        items.addAll(list)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemUi, onItemClick: (ItemUi) -> Unit) {
            binding.elementTextView.text = item.text

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}

class DiffUtilCallback(
    private val oldList: List<ItemUi>,
    private val newList: List<ItemUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size


    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return oldList[p0] == newList[p1]
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        return oldList[p0].id == newList[p1].id
    }
}