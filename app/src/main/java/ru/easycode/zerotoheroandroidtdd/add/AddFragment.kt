package ru.easycode.zerotoheroandroidtdd.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentAddBinding

class AddFragment : AbstractFragment<FragmentAddBinding>() {

    private lateinit var viewModel: AddViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().supportFragmentManager.popBackStack()
            viewModel.comeback()
        }
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).viewModel(AddViewModel::class.java)

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.addInputEditText.addTextChangedListener {
            it?.length?.let { length -> binding.saveButton.isEnabled = (length >= 3) }
        }

        binding.saveButton.setOnClickListener {
            viewModel.add(binding.addInputEditText.text.toString())
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}