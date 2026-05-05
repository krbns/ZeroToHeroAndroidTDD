package ru.easycode.zerotoheroandroidtdd.features.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentEditFolderBinding

class EditFolderFragment : AbstractFragment<FragmentEditFolderBinding>() {

    private lateinit var viewModel: EditFolderViewModel
    companion object {
        const val FOLDER_ID_ARG = "folderId"
    }

    private val folderId by lazy { requireArguments().getLong(FOLDER_ID_ARG) }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().supportFragmentManager.popBackStack()
            viewModel.comeback()
        }
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditFolderBinding {
        return FragmentEditFolderBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(EditFolderViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        viewModel.folderName().observe(viewLifecycleOwner) {
            binding.folderEditText.setText(it.title)
        }

        binding.saveFolderButton.setOnClickListener {
            viewModel.renameFolder(folderId, binding.folderEditText.text.toString())
        }

        binding.deleteFolderButton.setOnClickListener {
            viewModel.deleteFolder(folderId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}