package ru.easycode.zerotoheroandroidtdd.features.folders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFoldersListBinding

class FoldersListFragment : AbstractFragment<FragmentFoldersListBinding>() {

    private lateinit var viewModel: FolderListViewModel

    private val adapter = FoldersAdapter(
        onItemClick = { viewModel.folderDetails(it) }
    )

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFoldersListBinding {
        return FragmentFoldersListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(FolderListViewModel::class.java)

        viewModel.init()

        binding.foldersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.foldersRecyclerView.adapter = adapter

        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        binding.addButton.setOnClickListener {
            viewModel.addFolder()
        }
    }
}