package com.example.fakeecommece.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fakeecommece.R
import com.example.fakeecommece.databinding.FragmentAccountOptionsBinding


class AccountOptionsFragment : Fragment() {

   private var mBinding: FragmentAccountOptionsBinding? = null
   private val binding get() = mBinding!!

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      mBinding = FragmentAccountOptionsBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.btnRegister.setOnClickListener {
         findNavController().navigate(R.id.action_accountOptionsFragment_to_registerFragment)
      }
      binding.btnLogin.setOnClickListener {
         findNavController().navigate(R.id.action_accountOptionsFragment_to_loginFragment)
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      mBinding = null
   }


}