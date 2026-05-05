package ru.easycode.zerotoheroandroidtdd.features.folders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemFolderBinding

class FoldersAdapter(
    private val onItemClick: (FolderUi) -> Unit
) : RecyclerView.Adapter<FoldersAdapter.FolderViewHolder>() {
    private val items = ArrayList<FolderUi>()

    fun update(list: List<FolderUi>) {
        val diffUtil = DiffUtilCallback(items, list)
        val diff = DiffUtil.calculateDiff(diffUtil)
        items.clear()
        items.addAll(list)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(ItemFolderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size

    class FolderViewHolder(private val binding: ItemFolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FolderUi, onItemClick: (FolderUi) -> Unit) {
            binding.folderTitleTextView.text = item.title
            binding.folderCountTextView.text = item.notesCount.toString()

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class DiffUtilCallback(
        private val oldList: List<FolderUi>,
        private val newList: List<FolderUi>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
            return oldList[p0].id == newList[p1].id

        }

        override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
            return oldList[p0] == newList[p1]
        }
    }
}
