package com.example.fakeecommece.ui.util

import android.util.Patterns

/**
 * @author : Mingaleev D
 * @data : 13/12/2022
 */

fun validateEmail(email: String): RegisterValidation {
   if (email.isEmpty())
      return RegisterValidation.Failed("Email cannot be empty")

   if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
      return RegisterValidation.Failed("Wrong email format")

   return RegisterValidation.Success
}

fun validatePassword(password: String): RegisterValidation {
   if (password.isEmpty())
      return RegisterValidation.Failed("Password cannot be empty")

   if (password.length < 6)
      return RegisterValidation.Failed("Password should contains 6 char")

   return RegisterValidation.Success
}