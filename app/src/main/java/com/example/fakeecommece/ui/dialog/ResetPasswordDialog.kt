package com.example.fakeecommece.ui.dialog

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fakeecommece.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * @author : Mingaleev D
 * @data : 14/12/2022
 */


fun Fragment.setupButtonSheetDialog(
   onSendClick: (String) -> Unit
) {
   val dialog = BottomSheetDialog(requireContext(),R.style.DialogStyle)
   val view = layoutInflater.inflate(R.layout.reset_password_dialog, null)
   dialog.setContentView(view)
   dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
   dialog.show()

   val edEmail = view.findViewById<TextView>(R.id.edResetPass)
   val btnSend = view.findViewById<Button>(R.id.btnSendResetPass)
   val btnCancel = view.findViewById<Button>(R.id.btnCancelResetPass)

   btnSend.setOnClickListener {
      val email = edEmail.text.toString().trim()
      onSendClick(email)
      dialog.dismiss()
   }

   btnCancel.setOnClickListener {
      dialog.dismiss()
   }
}