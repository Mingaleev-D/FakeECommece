package com.example.fakeecommece.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fakeecommece.R
import com.example.fakeecommece.databinding.FragmentLoginBinding
import com.example.fakeecommece.ui.activities.ShoppingActivity
import com.example.fakeecommece.ui.dialog.setupButtonSheetDialog
import com.example.fakeecommece.ui.util.Resource
import com.example.fakeecommece.ui.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : Fragment() {

   private var mBinding: FragmentLoginBinding? = null
   private val binding get() = mBinding!!
   private val viewModel by viewModels<LoginViewModel>()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      mBinding = FragmentLoginBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      binding.tvDontHaveAccount.setOnClickListener {
         findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
      }

      binding.apply {
         btnLogin.setOnClickListener {
            val email = edEmailLogin.text.toString().trim()
            val password = edPasswordLogin.text.toString()
            viewModel.login(email, password)
         }
      }

      binding.tvForgotPasswordLogin.setOnClickListener {
         setupButtonSheetDialog { email ->
            viewModel.resetPassword(email)
         }
      }
      lifecycleScope.launchWhenStarted {
         viewModel.resetPassword.collect{
            when (it) {
               is Resource.Loading -> { }
               is Resource.Success -> {
                  Snackbar.make(requireView(),"Reset link wa sent to your email",Snackbar.LENGTH_LONG).show()
               }
               is Resource.Error   -> {
                  Snackbar.make(requireView(),"Error: ${it.message}",Snackbar.LENGTH_LONG).show()
               }
               else                -> Unit
            }
         }
      }

      lifecycleScope.launchWhenStarted {
         viewModel.login.collect {
            when (it) {
               is Resource.Loading -> {
                  binding.btnLogin.startAnimation()
               }
               is Resource.Success -> {
                  binding.btnLogin.revertAnimation()
                  Intent(requireContext(), ShoppingActivity::class.java).also { intent ->
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                     startActivity(intent)
                  }
               }
               is Resource.Error   -> {
                  Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                  binding.btnLogin.revertAnimation()
               }
               else                -> Unit
            }
         }
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      mBinding = null
   }


}