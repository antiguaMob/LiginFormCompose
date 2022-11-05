package com.antigua.myloginform.ui

import java.util.regex.Pattern

// __@__
// @
private  const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"


class EmailState: TextFieldState(
    validator = ::isEmailValid  ,
    errorMessage = ::emailErrorMessage ,
)

private fun isEmailValid(email: String) : Boolean {
    return  Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}
private fun emailErrorMessage(email: String) = "Email $email is invalid."