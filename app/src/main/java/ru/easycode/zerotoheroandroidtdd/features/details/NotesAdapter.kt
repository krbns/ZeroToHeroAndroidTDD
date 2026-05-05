package ru.easycode.zerotoheroandroidtdd.features.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemNoteBinding
import ru.easycode.zerotoheroandroidtdd.features.createnote.NoteUi

class NotesAdapter(
    private val onItemClick: (NoteUi) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val items = ArrayList<NoteUi>()

    fun update(list: List<NoteUi>) {
        val diffUtil = DiffUtilCallback(items, list)
        val diff = DiffUtil.calculateDiff(diffUtil)
        items.clear()
        items.addAll(list)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size

    class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NoteUi, onItemClick: (NoteUi) -> Unit) {
            binding.noteTitleTextView.text = item.title

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class DiffUtilCallback(
        private val oldList: List<NoteUi>,
        private val newList: List<NoteUi>
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
