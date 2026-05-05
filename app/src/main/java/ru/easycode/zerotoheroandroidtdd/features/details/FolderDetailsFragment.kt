package ru.easycode.zerotoheroandroidtdd.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderDetailsBinding

class FolderDetailsFragment : AbstractFragment<FragmentFolderDetailsBinding>() {

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().supportFragmentManager.popBackStack()
            viewModel.comeback()
        }
    }
    private lateinit var viewModel: FolderDetailsViewModel

    private val adapter = NotesAdapter(
        onItemClick = { viewModel.editNote(it) }
    )

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFolderDetailsBinding {
        return FragmentFolderDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(FolderDetailsViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.notesRecyclerView.adapter = adapter

        viewModel.init()

        binding.editFolderButton.setOnClickListener {
            viewModel.editFolder()
        }

        binding.addNoteButton.setOnClickListener {
            viewModel.createNote()
        }

        viewModel.noteList().observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        viewModel.folder().observe(viewLifecycleOwner) {
            binding.folderNameTextView.text = it.title
            binding.notesCountTextView.text = it.notesCount.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}