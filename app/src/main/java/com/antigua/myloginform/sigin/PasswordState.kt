package com.antigua.myloginform.sigin

class PasswordState: TextFieldState(
    validator = ::isPasswordValid  ,
    errorMessage = { passwordErrorMessage() },
) {
}

fun isPasswordValid(password: String) = password.length >= 4

private fun passwordErrorMessage() = "Password is invalid."
