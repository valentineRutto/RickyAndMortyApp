package com.example.rickyandmortyapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickyandmortyapp.R
import com.example.rickyandmortyapp.databinding.FragmentWelcomeBinding
import com.google.android.material.snackbar.Snackbar

class WelcomeFragment : Fragment() {

  private var _binding: FragmentWelcomeBinding? = null
  private val binding get() = _binding!!

  private val rickViewModel: RickyViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    registerBtnClicked()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun registerBtnClicked() {
    binding.registerBtn.setOnClickListener {
      validateAndSave()
      findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
  }

  private fun validateAndSave() {
    with(binding) {
      val name = nameEditText.text.toString()
      val funFact = funFactEditText.text.toString()

      val isValid = isValidString(name) && isValidString(funFact)
      if (isValid) {
        rickViewModel.saveUserPreferences(name, funFact, context = requireContext())
      } else {
        Snackbar.make(binding.root, "All fields are required", Snackbar.LENGTH_LONG).show()
      }
    }
  }

  private fun isValidString(text: String?): Boolean {
    var isValid = false
    if (text != null && text.isNotEmpty()) {
      isValid = true
    }
    return isValid
  }
}