package ru.easycode.zerotoheroandroidtdd.features.createnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : AbstractFragment<FragmentCreateNoteBinding>() {

    companion object {
        const val FOLDER_ID_ARG = "folderId"
    }

    private val folderId by lazy { requireArguments().getLong(FOLDER_ID_ARG) }
    private lateinit var viewModel: CreateNoteViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().supportFragmentManager.popBackStack()
            viewModel.comeback()
        }
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateNoteBinding {
        return FragmentCreateNoteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(CreateNoteViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.saveNoteButton.setOnClickListener {
            viewModel.createNote(folderId, binding.createNoteEditText.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}