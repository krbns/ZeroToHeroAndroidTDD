package ru.easycode.zerotoheroandroidtdd.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentDeleteBinding

class DeleteFragment : AbstractFragment<FragmentDeleteBinding>() {

    private lateinit var viewModel: DeleteViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().supportFragmentManager.popBackStack()
            viewModel.comeback()
        }
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDeleteBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).viewModel(DeleteViewModel::class.java)
        val itemId = arguments?.getLong(ITEM_ID_KEY) ?: return

        viewModel.init(itemId)

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.itemTitleTextView.text = it
        }

        binding.deleteButton.setOnClickListener {
            viewModel.delete(itemId)
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }

    companion object {
        private const val ITEM_ID_KEY = "item_id"
        fun newInstance(itemId: Long) = DeleteFragment().apply {
            arguments = Bundle().apply { putLong(ITEM_ID_KEY, itemId) }
        }
    }
}