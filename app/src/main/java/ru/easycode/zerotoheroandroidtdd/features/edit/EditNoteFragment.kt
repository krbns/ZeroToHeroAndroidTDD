package ru.easycode.zerotoheroandroidtdd.features.edit

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentEditNoteBinding

class EditNoteFragment : AbstractFragment<FragmentEditNoteBinding>() {

    companion object {
        const val NOTE_ID_ARG = "folderId"
    }

    private val noteId by lazy { requireArguments().getLong(NOTE_ID_ARG) }

    private lateinit var viewModel: EditNoteViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().supportFragmentManager.popBackStack()
            viewModel.comeback()
        }
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditNoteBinding {
        return FragmentEditNoteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(EditNoteViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        viewModel.init(noteId)

        viewModel.noteName().observe(viewLifecycleOwner) {
            binding.noteEditText.setText(it)
        }

        binding.saveNoteButton.setOnClickListener {
            viewModel.renameNote(noteId, binding.noteEditText.text.toString())
        }

        binding.deleteNoteButton.setOnClickListener {
            viewModel.deleteNote(noteId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}