package com.antigua.myloginform.sigin

import java.util.regex.Pattern

// __@__
// @
private  const val USERNAME_VALIDATION_REGEX = "^[a-zA-z0-9]{3,15}$"


class UsernameState: TextFieldState(
    validator = ::isUsernameValid  ,
    errorMessage = ::usernameErrorMessage ,
)

private fun isUsernameValid(username: String) : Boolean {
    return  Pattern.matches(USERNAME_VALIDATION_REGEX, username)
}
private fun usernameErrorMessage(username: String) = "$username is invalid."