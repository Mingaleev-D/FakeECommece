package com.example.fakeecommece.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fakeecommece.R
import com.example.fakeecommece.databinding.FragmentIntroductionBinding


class IntroductionFragment : Fragment() {

   private var mBinding: FragmentIntroductionBinding? = null
   private val binding get() = mBinding!!

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      mBinding = FragmentIntroductionBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.btnStart.setOnClickListener {
         findNavController().navigate(R.id.action_introductionFragment_to_accountOptionsFragment)
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      mBinding = null
   }


}