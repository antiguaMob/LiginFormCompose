package com.antigua.myloginform.ui

class PasswordState: TextFieldState(
    validator = ::isPasswordValid  ,
    errorMessage = { passwordErrorMessage() },
) {
}

fun isPasswordValid(password: String) = password.length >= 4

private fun passwordErrorMessage() = "Password is invalid."
