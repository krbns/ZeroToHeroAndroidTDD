package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding
import ru.easycode.zerotoheroandroidtdd.ui.adapter.ItemsAdapter

class ListFragment : AbstractFragment<FragmentListBinding>() {
    private lateinit var viewModel: ListViewModel

    private val adapter = ItemsAdapter()
    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).viewModel(ListViewModel::class.java)
        savedInstanceState?.let {
            viewModel.restore(BundleWrapper.Base(it))
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }
        binding.addButton.setOnClickListener {
            viewModel.create()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }
}