package ru.easycode.zerotoheroandroidtdd.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentDetailsBinding

class DetailsFragment : AbstractFragment<FragmentDetailsBinding>() {

    private lateinit var viewModel: DetailsViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().supportFragmentManager.popBackStack()
            viewModel.comeback()
        }
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).viewModel(DetailsViewModel::class.java)
        val itemId = arguments?.getLong(ITEM_ID_KEY) ?: return

        viewModel.init(itemId)

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.itemTextView.text = it
            binding.itemInputEditText.setText(it)
        }

        binding.itemInputEditText.addTextChangedListener {
            binding.updateButton.isEnabled = (binding.itemInputEditText.text?.length ?: 0) >= 0
        }

        binding.updateButton.setOnClickListener {
            viewModel.update(itemId, binding.itemInputEditText.text.toString())
            requireActivity().supportFragmentManager.popBackStack()
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
        fun newInstance(itemId: Long) = DetailsFragment().apply {
            arguments = Bundle().apply { putLong(ITEM_ID_KEY, itemId) }
        }
    }
}