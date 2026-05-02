package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemLayoutBinding


class ItemsAdapter :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private val items = ArrayList<CharSequence>()


    fun addAll(list: List<CharSequence>) {
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

class DiffUtilCallback(
    private val oldList: List<CharSequence>,
    private val newList: List<CharSequence>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size


    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return oldList[p0] == newList[p1]
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        return oldList[p0] == newList[p1]
    }
}