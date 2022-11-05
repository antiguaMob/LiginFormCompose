package com.antigua.myloginform.ui


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antigua.myloginform.R
import com.antigua.myloginform.ui.theme.darkBlue
import com.antigua.myloginform.ui.theme.strongBlue

@Preview(showSystemUi = true)
@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val emailState = remember { EmailState() }
        val passwordState = remember { PasswordState() }
        val localFocusManager = LocalFocusManager.current
        Title()
        // Email
        Email(
            email = emailState.text,
            error = emailState.error,
            onEmailChanged = {
                emailState.text = it
                emailState.validate()
            },
            onImeAction = {
                localFocusManager.moveFocus(FocusDirection.Down)
            },
        )
        // Password
        Password(
            password = passwordState.text,
            error = passwordState.error,
            onPasswordChanged = {
                passwordState.text = it
                passwordState.validate()
            },
            onImeAction = {
                localFocusManager.clearFocus()
                if(emailState.isValid() && passwordState.isValid()){
                    signIn(emailState.text, passwordState.text)
                }
            },
        )
        // Button
        SignInButton(
            enabled = (emailState.isValid() && passwordState.isValid()),
            email = emailState.text,
            password = passwordState.text
        )
    }
}

fun signIn(email: String, password: String) {
Log.d("TEST","email $email, password $password")
}

@Composable
fun Title() {
    Text(
        text = stringResource(R.string.sign_in_welcome_text),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = strongBlue
    )
    Spacer(modifier = Modifier
        .padding(start = 16.dp))
}

@Composable
fun Email(
    email : String,
    error: String?,
    onEmailChanged: (String)->Unit,
    onImeAction: () -> Unit
    ) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { onEmailChanged(it) },
            label = { Text(text = stringResource(R.string.email_hint)) },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(onNext = {
                onImeAction ()
            }),
            isError = error != null,

        )
        error?.let { ErrorField(it) }
    }
}

@Composable
fun Password(
    password : String,
    error: String?,
    onPasswordChanged: (String)->Unit,
    onImeAction: () -> Unit
) {
    val showPassword = remember{ mutableStateOf(false) }
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { onPasswordChanged(it) },
            label = { Text(text = stringResource(R.string.password_hint))},
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if(showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                if(showPassword.value){
                    IconButton(onClick = { showPassword.value = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = stringResource(R.string.hide_password)
                        )
                    }
                } else {
                    IconButton(onClick = { showPassword.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = stringResource(R.string.show_password)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                onImeAction ()
            }),
            isError = error !=null
        )
        error?.let { ErrorField(it) }
    }

}

@Composable
fun ErrorField(error: String) {
    Text(
        text = error,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(color = MaterialTheme.colors.error)
    )
}

@Composable
fun SignInButton(enabled: Boolean, email: String, password: String) {
    Button(
        onClick = { signIn(email, password)  },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = darkBlue,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
    ) {
       Text(text = stringResource(R.string.sign_in))
    }
}






