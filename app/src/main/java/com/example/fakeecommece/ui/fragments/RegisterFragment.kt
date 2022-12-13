package com.example.fakeecommece.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fakeecommece.R
import com.example.fakeecommece.data.model.User
import com.example.fakeecommece.databinding.FragmentRegisterBinding
import com.example.fakeecommece.ui.util.Resource
import com.example.fakeecommece.ui.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

   private var mBinding: FragmentRegisterBinding? = null
   private val binding get() = mBinding!!

   private val viewModel by viewModels<RegisterViewModel>()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      mBinding = FragmentRegisterBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.apply {
         btnRegister.setOnClickListener {
            val user = User(
               edFirstName.text.toString().trim(),
               edLastName.text.toString().trim(),
               edEmailReg.text.toString().trim(),
            )
            val password = edPasswordReg.text.toString()
            viewModel.createAccountWithEmailAndPassword(user,password)
         }
      }
      lifecycleScope.launchWhenStarted {
         viewModel.register.collect{
            when(it) {
               is Resource.Error   -> {
                  Log.e("TAG", "onViewCreated:${it.message.toString()}")
                  binding.btnRegister.revertAnimation()
               }
               is Resource.Loading -> {
                  binding.btnRegister.startAnimation()
               }
               is Resource.Success -> {
                  Log.d("test", "onViewCreated:${it.data.toString()}")
                  binding.btnRegister.revertAnimation()
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